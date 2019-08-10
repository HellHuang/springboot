package com.example.images.images.Chapter02;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ApiController {
    @GetMapping("/images")
    public Flux<Image> getImages()
    {
        return Flux.just(
                new Image("1","iamge1"),
                new Image("2","image2"),
                new Image("3","image3")
        );
    }
    /*
    @PostMapping("/images")
    public Mono<Void> create(@RequestBody Flux<Image> images)
    {
        return images.map(image ->{
            System.out.println(image.name);
            return image;
                }
        ).then();
    }*/
}
