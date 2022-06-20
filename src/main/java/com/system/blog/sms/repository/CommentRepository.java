package com.system.blog.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.system.blog.sms.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
