package br.com.renato.lst_rest.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renato.lst_rest.Dtos.UsuarioDto;
import br.com.renato.lst_rest.services.UsuarioService;

@RestController
@RequestMapping(path = {"/usuario","/Usuario"})
public class UsuarioController {
	@Autowired
	UsuarioService service;
	
	@GetMapping(path = "/{id}")
	public Object buscarPorId(@PathVariable(value = "id",required = true) Long id) {
		return service.buscarPorId(id);
	}
	
	/*
	@PostMapping
	public ResponseEntity<Object> save(@RequestBody Map<String, Object> dicDados){
		return service.save(dicDados);
		}
	*/
	
	@PostMapping
	public ResponseEntity<Object> save(@RequestBody UsuarioDto userDto){
		return service.save(userDto);
		}
	
	
	

}
