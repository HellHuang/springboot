package com.example.images.images.Chapter03;

import com.example.images.images.Chapter03.Image;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
public class InitDatabase {
    @Bean
    CommandLineRunner init(MongoOperations operations)
    {
        return args -> {
            operations.dropCollection(Image.class);
            operations.insert(new Image("1","1.jpg"));
            operations.insert(new Image("2","2.jpg"));
            operations.findAll(Image.class).forEach(image ->{
                System.out.println("iiiimage" + image.toString());
            });
        };
    }
}
