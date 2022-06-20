package com.system.blog.sms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.system.blog.sms.dto.CommentDTO;
import com.system.blog.sms.entity.Comment;
import com.system.blog.sms.entity.Publication;
import com.system.blog.sms.exceptions.BlogAppException;
import com.system.blog.sms.exceptions.ResourceNotFoundException;
import com.system.blog.sms.repository.CommentRepository;
import com.system.blog.sms.repository.PublicationRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private PublicationRepository publicationRepository;

	@Override
	public CommentDTO createComment(long publicationId, CommentDTO commentDTO) {
		// TODO Auto-generated method stub
		Comment comment = mapperEntity(commentDTO);
		Publication publication = publicationRepository.findById(publicationId)
				.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", publicationId));
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

	@Override
	public List<CommentDTO> getCommentsForPublicationId(long publicationId) {
		// TODO Auto-generated method stub
		List<Comment> comments = commentRepository.findByPublicationId(publicationId);
		return comments.stream().map(comment -> mapperDTO(comment)).collect(Collectors.toList());
	}

	@Override
	public CommentDTO getCommentForId(Long publicationId, Long commentId) {
		// TODO Auto-generated method stub
		Publication publication = publicationRepository.findById(publicationId)
				.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", publicationId));
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

		if (!comment.getPublication().getId().equals(publication.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comment don't belong to the publication");
		}

		return mapperDTO(comment);
	}

	@Override
	public CommentDTO updateComment(Long publicationId, Long commentId, CommentDTO requestOfComment) {
		// TODO Auto-generated method stub
		Publication publication = publicationRepository.findById(publicationId)
				.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", publicationId));
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

		if (!comment.getPublication().getId().equals(publication.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comment don't belong to the publication");
		}

		comment.setName(requestOfComment.getName());
		comment.setEmail(requestOfComment.getEmail());
		comment.setBody(requestOfComment.getBody());

		Comment commentUpdated = commentRepository.save(comment);
		return mapperDTO(commentUpdated);
	}

	@Override
	public void deleteComment(Long publicationId, Long commentId) {
		// TODO Auto-generated method stub
		Publication publication = publicationRepository.findById(publicationId)
				.orElseThrow(() -> new ResourceNotFoundException("Publication", "id", publicationId));
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

		if (!comment.getPublication().getId().equals(publication.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comment don't belong to the publication");
		}
		
		commentRepository.delete(comment);
	}
}
