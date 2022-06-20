package com.system.blog.sms.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.system.blog.sms.entity.Publication;

public interface PublicationRepository extends JpaRepository<Publication,Long>{
	
}
