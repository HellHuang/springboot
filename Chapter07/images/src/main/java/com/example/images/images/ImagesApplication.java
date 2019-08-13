package com.example.images.images;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;

@SpringCloudApplication
public class ImagesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImagesApplication.class, args);
	}
	@GetMapping("test1")
	public String get(){
		return "tes1";
	}
	@Bean
	HiddenHttpMethodFilter hiddenHttpMethodFilter()
	{
		return new HiddenHttpMethodFilter();
	}
	//HiddenHttpMethodFilter Spring bean to make the HTTP DELETE methods work properly.
	//DELETE is not a valid action for an HTML5 FORM, so Thymeleaf creates
	//a hidden input field containing our desired verb while the enclosing
	//form uses an HTML5 POST. This gets transformed by Spring during the
	//web call, resulting in the @DeleteMapping method being properly invoked
	//with no effort on our end.
}
