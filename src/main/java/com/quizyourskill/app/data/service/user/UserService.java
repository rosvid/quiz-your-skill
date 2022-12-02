package com.quizyourskill.app.data.service.user;

import com.quizyourskill.app.data.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService{
   private final UserRepository userRepository;

   @Autowired
   public UserService(UserRepository repository){
      this.userRepository = repository;
   }

   public Optional<User> get(Integer id){
      return userRepository.findById(id);
   }

   public User update(User entity){
      return userRepository.save(entity);
   }

   public void delete(Integer id){
      userRepository.deleteById(id);
   }

   public Page<User> list(Pageable pageable){
      return userRepository.findAll(pageable);
   }

   public int count(){
      return (int) userRepository.count();
   }
}