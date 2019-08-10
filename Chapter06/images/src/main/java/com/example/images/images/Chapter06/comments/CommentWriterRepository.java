package com.example.images.images.Chapter06.comments;


import org.springframework.data.repository.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentWriterRepository extends Repository<Comment, String>
{
    Mono<Comment> save(Comment newComment);
    Mono<Comment> findById(String id);
    Flux<Comment> findByImageId(String id);
}
