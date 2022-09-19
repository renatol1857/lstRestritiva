package br.com.renato.lst_rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renato.lst_rest.models.Usuario;
import br.com.renato.lst_rest.services.UsuarioService;

@RestController
@RequestMapping(path = {"/usuario","/Usuario"})
public class UsuarioController {
	@Autowired
	UsuarioService service;
	
	@GetMapping(path = "/{id}")
	private ResponseEntity<Usuario> BuscarPorId(@PathVariable(value = "id",required = true) Long id) {
		Usuario obj = service.BuscarPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(obj);
	}
	

}
