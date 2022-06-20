package com.system.blog.sms.service;

import java.util.List;

import com.system.blog.sms.dto.CommentDTO;

public interface CommentService {

	public CommentDTO createComment(long publicationId,CommentDTO commentDTO);
	public List<CommentDTO> getCommentsForPublicationId(long publicationId);
}
