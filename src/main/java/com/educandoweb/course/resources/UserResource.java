package com.educandoweb.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.User;

@RestController  //indicar que é o rest é controlado por um controller
@RequestMapping(value = "/users") //caminho do recurso
public class UserResource {

	@GetMapping 
	public ResponseEntity<User> findAll(){   //Response tipo especifico do spring para retornar resposta de requisiçoes web
		User u = new User(1L,"Maria", "maria@gmail.com", "9999999", "12345");
		return ResponseEntity.ok().body(u);
	}
	
}
