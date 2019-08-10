package com.example.images.images.Chapter06;

import com.example.images.images.Chapter06.comments.CommentWriterRepository;
import com.example.images.images.Chapter06.images.ImageService;
import com.example.images.images.Chapter06.comments.Comment;
import com.example.images.images.Chapter06.images.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.HashMap;

@Profile("dev")
@Controller
public class HomeController {
    private static final String BASE_PATH = "/images";
    private static final String FILENAME ="{filename:.+}";
    private ImageService imageService;
    private CommentWriterRepository commentWriterRepository;
    /*public HomeController(ImageService imageService)
    {
        this.imageService = imageService ;
    }*///for Chapter03
    public HomeController(ImageService imageService,CommentWriterRepository commentWriterRepository)
    {
        this.imageService = imageService ;
        this.commentWriterRepository = commentWriterRepository;
    }
    @Data
    @AllArgsConstructor
    static class CommandAndImage {
        Comment comment;
        Image image;
    }
    @GetMapping(value = BASE_PATH +"/"+FILENAME +"/raw",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public Mono<ResponseEntity<?>> oneRawImage(@PathVariable String filename)
    {
        return imageService.findOneImage(filename).map
                (
                    resource ->
                    {
                        try
                        {
                            return ResponseEntity.ok().contentLength(resource.contentLength()).
                                    body(new InputStreamResource(resource.getInputStream()));
                        }
                        catch(IOException e)
                        {
                            return ResponseEntity.badRequest().body("Could not find " +filename + e.getMessage());
                        }
                    }
                );
    }
    @PostMapping(value= BASE_PATH)
    public Mono<String> createFile(@RequestPart(name="file")Flux<FilePart> files)
    {
        return imageService.createImage(files).then(Mono.just("redirect:/"));
    }
    @DeleteMapping(BASE_PATH+"/"+FILENAME)
    public Mono<String> deleteFile(@PathVariable String filename)
    {
        return imageService.deleteImage(filename).then(Mono.just("redirect:/"));
    }
    @GetMapping("/")
    public Mono<String> index(Model model)
    {
        //model.addAttribute("images",imageService.findAllImages()); //for chapter 03

        model.addAttribute("images",
                imageService
                        .findAllImages()
                        .flatMap(image ->
                                Mono.just(image)
                                        .zipWith(commentWriterRepository.findByImageId(
                                                image.getId()).collectList()))
                        .map(imageAndComments -> new HashMap<String, Object>(){{
                            put("id", imageAndComments.getT1().getId());
                            put("name", imageAndComments.getT1().getName());
                            put("comments",
                                    imageAndComments.getT2());
                        }}));

        model.addAttribute("extra","DevTools can also detect code changes too");
        return Mono.just("index");
    }
}
