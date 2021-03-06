package com.system.blog.sms.service;

import java.util.List;

import com.system.blog.sms.dto.CommentDTO;

public interface CommentService {

	public CommentDTO createComment(long publicationId, CommentDTO commentDTO);

	public List<CommentDTO> getCommentsForPublicationId(long publicationId);

	public CommentDTO getCommentForId(Long publicationId, Long commentId);

	public CommentDTO updateComment(Long publicationId, Long commentId, CommentDTO requestOfComment);
	
	public void deleteComment(Long publicationId, Long commentId);
}
