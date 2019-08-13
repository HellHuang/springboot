package com.example.images.images;

import com.example.images.images.images.Comment;
import com.example.images.images.images.ImageService;
import com.example.images.images.images.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


@Controller
public class HomeController {

    private final ImageService imageService;


    private final RestTemplate restTemplate;

    public HomeController(ImageService imageService,
                          RestTemplate restTemplate) {
        this.imageService = imageService;
        this.restTemplate = restTemplate;
    }


    @GetMapping("/")
    public Mono<String> index(Model model) {
        model.addAttribute("images",
                imageService
                        .findAllImages()
                        .map(image -> new HashMap<String, Object>() {{
                            put("id", image.getId());
                            put("name", image.getName());
                            put("comments",

                                    restTemplate.exchange(
                                            "http://COMMENTS/comments/{imageId}",
                                            HttpMethod.GET,
                                            null,
                                            new ParameterizedTypeReference<List<Comment>>() {},
                                            image.getId()).getBody());

                        }})
        );
        //COMMENTS is the logical name that our comments microservice registered itself with in Eureka
        return Mono.just("index");
    }
}

