package com.iktpreobuka.elektronskidnevnik.controllers;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronskidnevnik.entities.UserEntity;
import com.iktpreobuka.elektronskidnevnik.repositories.UserRepository;

@RestController
@RequestMapping("/")
public class UserController {
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method=RequestMethod.GET, value="/users/{id}")
	public ResponseEntity<?> findUserById(@PathVariable Integer userId){
		for(UserEntity user:userRepository.findAll()) {
			if(user.getUserId()==userId)
				return new ResponseEntity <> (user, HttpStatus.OK); 
		}
		return new ResponseEntity <> (null, HttpStatus.OK); 
		 
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/users")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserEntity user){
		
		UserEntity ue = new UserEntity();
		ue.setUserId(user.getUserId());
		ue.setName(user.getName());
		ue.setSurname(user.getSurname());
		ue.setJmbg(user.getJmbg());
		ue.setAddress(user.getAddress());
		ue.setCity(user.getCity());
		ue.setEmail(user.getEmail());
		ue.setPhoneNumber(user.getPhoneNumber());
		ue.setRole(user.getRole());
		ue.setUsername(user.getUsername());
		ue.setPassword(user.getPassword());
		userRepository.save(ue);
		
		logger.debug("new user added");
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
}
	
