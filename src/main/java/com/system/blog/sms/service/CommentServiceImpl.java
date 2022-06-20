package com.system.blog.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.blog.sms.dto.CommentDTO;
import com.system.blog.sms.entity.Comment;
import com.system.blog.sms.entity.Publication;
import com.system.blog.sms.exceptions.ResourceNotFoundException;
import com.system.blog.sms.repository.CommentRepository;
import com.system.blog.sms.repository.PublicationRepository;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PublicationRepository publicationRepository;
	
	@Override
	public CommentDTO createComment(long publicationId, CommentDTO commentDTO) {
		// TODO Auto-generated method stub
		Comment comment = mapperEntity(commentDTO);
		Publication publication = publicationRepository.findById(publicationId)
				.orElseThrow(() -> new ResourceNotFoundException("Publication","id",publicationId));
		comment.setPublication(publication);
		Comment newComment = commentRepository.save(comment);
		return mapperDTO(newComment);
	}
	
	private CommentDTO mapperDTO(Comment comment) {
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setId(comment.getId());
		commentDTO.setName(comment.getName());
		commentDTO.setEmail(comment.getEmail());
		commentDTO.setBody(comment.getBody());
		
		return commentDTO;
	}

	private Comment mapperEntity(CommentDTO commentDTO) {
		Comment comment = new Comment();
		comment.setId(commentDTO.getId());
		comment.setName(commentDTO.getName());
		comment.setEmail(commentDTO.getEmail());
		comment.setBody(commentDTO.getBody());
		
		return comment;
	}
}