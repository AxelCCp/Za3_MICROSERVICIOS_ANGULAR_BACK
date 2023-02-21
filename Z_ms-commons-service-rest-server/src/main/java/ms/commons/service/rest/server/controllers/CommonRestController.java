package ms.commons.service.rest.server.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ms.commons.service.rest.server.models.services.ICommonService;

public class CommonRestController<E,S extends ICommonService<E>>{

	@GetMapping
	public ResponseEntity<?>list(){
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?>getById(@PathVariable Long id){
		Optional<E> o = service.getById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(o.get());
	}
	
	@PostMapping
	public ResponseEntity<?>create(@RequestBody E entity){
		E entitydb = service.save(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entitydb);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>deleteById(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();	
	}
	
	@GetMapping("/page")
	public ResponseEntity<?>listPageable(Pageable pageable){
		return ResponseEntity.ok().body(service.findAll(pageable));
	}
	
	protected ResponseEntity<?>validation(BindingResult result){
		Map<String,Object>errors = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errors.put(err.getField(), "The field --- " + err.getField() + " --- " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errors);
	}
	
	@Autowired
	protected S service;
}
