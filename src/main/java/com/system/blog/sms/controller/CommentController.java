package com.system.blog.sms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<CommentDTO> getCommentForId(@PathVariable(value = "publicationId") Long publicationId,
			@PathVariable(value = "id") Long commentId) {
		CommentDTO commentDTO = commentService.getCommentForId(publicationId, commentId);
		return new ResponseEntity<>(commentDTO, HttpStatus.OK);
	}

	@PostMapping("/publications/{publicationId}/comments")
	public ResponseEntity<CommentDTO> saveComment(@PathVariable(value = "publicationId") long publicationId,
			@Valid @RequestBody CommentDTO commentDTO) {
		return new ResponseEntity<>(commentService.createComment(publicationId, commentDTO), HttpStatus.CREATED);
	}

	@PutMapping("publications/{publicationId}/comments/{id}")
	public ResponseEntity<CommentDTO> updateComment(@PathVariable(value = "publicationId") Long publicationId,
			@PathVariable(value = "id") Long commentId,@Valid @RequestBody CommentDTO commentDTO) {
		CommentDTO commentUpdated = commentService.updateComment(publicationId, commentId, commentDTO);
		return new ResponseEntity<>(commentUpdated, HttpStatus.OK);
	}

	@DeleteMapping("publications/{publicationId}/comments/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable(value = "publicationId") Long publicationId,
			@PathVariable(value = "id") Long commentId) {
		commentService.deleteComment(publicationId, commentId);
		return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
	}
}
