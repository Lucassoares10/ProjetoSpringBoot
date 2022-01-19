package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.services.ProductService;

@RestController  //indicar que é o rest é controlado por um controller
@RequestMapping(value = "/products") //caminho do recurso
public class ProductResource {

	@Autowired
	private ProductService service;
	
	@GetMapping 
	public ResponseEntity<List<Product>> findAll(){   //Response tipo especifico do spring para retornar resposta de requisiçoes web
		List<Product> list = service.findAll();       //busca todos user passando uma lista de user
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id){ //buscar user por id
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
