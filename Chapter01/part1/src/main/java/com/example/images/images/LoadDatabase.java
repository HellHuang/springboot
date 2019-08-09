package com.example.images.images;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Configuration
public class LoadDatabase {
    @Bean
    CommandLineRunner init(ChapterRepository repository)
    {
        return args -> {
            Flux.just(
                    new Chapter("Quick start with java!"),
                    new Chapter("Quick start with spring boot!")

            ).flatMap(repository::save).subscribe(System.out::println);
        };
    }
}
