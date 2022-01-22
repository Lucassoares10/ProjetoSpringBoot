package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.resources.exceptions.DataBaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

@Service    //faz a mesmo que o component para registrar a dependencia
public class UserService {

	@Autowired
	private UserRepository repository; //para utilizarmos o repository mais a frente.
	
	//buscar todos users
	public List<User> findAll(){
		return repository.findAll();
	}
	
	//buscar users por id
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id)); // tenta da o get caso n tenha retorno, da a exceção feita em expressao lambda
	}
	
	public User insert(User obj) { //pegando o usuario e salvando com o repository
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {  //tipo especifica de exception que foi capturada ao retornar um codigo invalido
			throw new ResourceNotFoundException(id); //apos pegada o tipo de exception, lançada o tipo que foi implementado para retorno.
		}catch(DataIntegrityViolationException e){ //exception de violação de integridade de dados
			//e.printStackTrace(); ajuda o programador a entender aonde o problema ocorreu
			throw new DataBaseException(e.getMessage());
		}
	}
	
	
	public User update(Long id, User obj) {
		User entity = repository.getOne(id);
		updateData(entity,obj);
		return repository.save(entity);
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
 