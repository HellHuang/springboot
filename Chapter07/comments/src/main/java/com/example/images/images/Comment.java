package com.example.images.images;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Comment {

	@Id private String id;
	private String imageId;
	private String comment;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
// end::code[]