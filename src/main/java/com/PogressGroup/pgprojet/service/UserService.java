package com.PogressGroup.pgprojet.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.PogressGroup.pgprojet.model.User;
import com.PogressGroup.pgprojet.web.dto.UserRegistrationDto;



public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);

}
