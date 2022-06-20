package com.system.blog.sms.service;

import com.system.blog.sms.dto.CommentDTO;

public interface CommentService {

	public CommentDTO createComment(long publicationId,CommentDTO commentDTO);
	
}
