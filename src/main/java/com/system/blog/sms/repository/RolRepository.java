package com.system.blog.sms.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.system.blog.sms.entity.Rol;
import com.system.blog.sms.entity.User;

public interface RolRepository extends JpaRepository<User, Long>{

	public Optional<Rol> findByName(String name);
}
