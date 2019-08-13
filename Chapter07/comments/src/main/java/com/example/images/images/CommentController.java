
package com.example.images.images;

import reactor.core.publisher.Flux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CommentController {

	private final CommentRepository repository;

	public CommentController(CommentRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/comments/{imageId}")
	public Flux<Comment> comments(@PathVariable String imageId)
	{
		return repository.findByImageId(imageId);
	}
}

