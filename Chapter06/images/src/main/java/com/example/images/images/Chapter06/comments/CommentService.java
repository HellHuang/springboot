package com.example.images.images.Chapter06.comments;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private CommentWriterRepository commentWriterRepository ;
    public CommentService( CommentWriterRepository commentWriterRepository )
    {
        this.commentWriterRepository = commentWriterRepository;
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "learning-spring-boot"),
            key = "comments.new"
    ))
    public void save(Comment newComment) {
        commentWriterRepository
                .save(newComment)
                .log("commentService-save")
                .subscribe();
    }
    @Bean
    Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    CommandLineRunner setUp(MongoOperations operations)
    {
        return args ->{
            operations.dropCollection(Comment.class);
        };//To start our application with a clean slate
    }

}
