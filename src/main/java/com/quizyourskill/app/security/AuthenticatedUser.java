package com.quizyourskill.app.security;

import com.quizyourskill.app.data.entity.user.User;
import com.quizyourskill.app.data.service.user.UserRepository;
import com.vaadin.flow.component.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticatedUser{
   private final UserRepository userRepository;

   @Autowired
   public AuthenticatedUser(UserRepository userRepository){
      this.userRepository = userRepository;
   }

   private Optional<Authentication> getAuthentication(){
      SecurityContext context = SecurityContextHolder.getContext();
      return Optional.ofNullable(context.getAuthentication())
            .filter(authentication -> !(authentication instanceof AnonymousAuthenticationToken));
   }

   public Optional<User> get(){
      return getAuthentication().map(authentication -> userRepository.findByUsername(authentication.getName()));
   }

   public void logout(){
      UI.getCurrent().getPage().setLocation(SecurityConfiguration.LOGOUT_URL);
      SecurityContextHolder.clearContext();
   }
}