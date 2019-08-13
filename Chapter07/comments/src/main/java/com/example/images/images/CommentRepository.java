package com.example.images.images;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.repository.Repository;


public interface CommentRepository
	extends Repository<Comment, String> {

	Flux<Comment> findByImageId(String imageId);

	Flux<Comment> saveAll(Flux<Comment> newComment);

	Mono<Comment> save(Mono<Comment> newComment);
	Mono<Comment> findById(String id);

	Mono<Void> deleteAll();
}

