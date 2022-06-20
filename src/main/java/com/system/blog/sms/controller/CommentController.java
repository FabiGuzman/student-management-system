package com.system.blog.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.blog.sms.dto.CommentDTO;
import com.system.blog.sms.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@GetMapping("publications/{publicationId}/comments")
	public List<CommentDTO> listCommentsForPublicationId(@PathVariable(value = "publicationId") long publicationId) {
		return commentService.getCommentsForPublicationId(publicationId);
	}
	
	@GetMapping("publications/{publicationId}/comments/{id}")
	public ResponseEntity<CommentDTO> getCommentForId(@PathVariable(value = "publicationId") Long publicationId,@PathVariable(value = "id") Long commentId) {
		CommentDTO commentDTO = commentService.getCommentForId(publicationId,commentId);
		return new ResponseEntity<>(commentDTO,HttpStatus.OK); 
	}
	
	@PostMapping("/publications/{publicationId}/comments")
	public ResponseEntity<CommentDTO> saveComment(@PathVariable(value = "publicationId") long publicationId, @RequestBody CommentDTO commentDTO) {
		return new ResponseEntity<>(commentService.createComment(publicationId,commentDTO),HttpStatus.CREATED);
	}
}
