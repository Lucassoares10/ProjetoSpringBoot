package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

@RestController  //indicar que é o rest é controlado por um controller
@RequestMapping(value = "/users") //caminho do recurso
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping 
	public ResponseEntity<List<User>> findAll(){   //Response tipo especifico do spring para retornar resposta de requisiçoes web
		List<User> list = service.findAll();       //busca todos user passando uma lista de user
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){ //buscar user por id
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//Inserindo Objetos com Post
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){
		obj = service.insert(obj);
		//criar uri para retornar 201(novo recurso)
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri(); //criada uri para o novo recurso
		return ResponseEntity.created(uri).body(obj);
	}
	
	//Deletando objetos pelo id com delete
	@DeleteMapping (value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id ){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
