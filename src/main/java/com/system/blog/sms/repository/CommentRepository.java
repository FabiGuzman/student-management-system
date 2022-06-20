package com.system.blog.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.system.blog.sms.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	public List<Comment> findByPublicationId(long publicationId);
}
