package com.quizyourskill.app.data.service.user;

import com.quizyourskill.app.data.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
   User findByUsername(String username);
}