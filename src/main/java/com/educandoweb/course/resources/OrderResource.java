package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.services.OrderService;

@RestController  //indicar que é o rest é controlado por um controller
@RequestMapping(value = "/orders") //caminho do recurso
public class OrderResource {

	@Autowired
	private OrderService service;
	
	@GetMapping 
	public ResponseEntity<List<Order>> findAll(){   //Response tipo especifico do spring para retornar resposta de requisiçoes web
		List<Order> list = service.findAll();       //busca todos user passando uma lista de order
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id){ //buscar user por id
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
