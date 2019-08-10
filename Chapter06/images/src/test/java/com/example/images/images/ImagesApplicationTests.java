package com.example.images.images;

import com.example.images.images.Chapter06.images.Image;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImagesApplicationTests {


	public void contextLoads() {
	}
	@Test
	public void imageManagedByLombokShouldbeWork()
	{
		Image image  =new Image("id","filename.jpg");
		assertThat(image.toString().equals("filename.jpg")) ;
	}

}
