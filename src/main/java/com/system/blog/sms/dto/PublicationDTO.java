package com.system.blog.sms.dto;

import java.util.Set;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;
import com.system.blog.sms.entity.Comment;

public class PublicationDTO {

	private Long id;
	
	@NotEmpty
	@Size(min = 2,message = "The title of publication must be has at least two characters")
	private String title;
	
	@NotEmpty
	@Size(min = 10,message = "The title of description must be has at least ten characters")	
	private String description;
	
	@NotEmpty
	private String content;
	
	private Set<Comment> comments;

	public PublicationDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

}
