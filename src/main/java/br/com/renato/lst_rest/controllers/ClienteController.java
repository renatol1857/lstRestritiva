package br.com.renato.lst_rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renato.lst_rest.models.ClienteModel;
import br.com.renato.lst_rest.services.ClienteService;

@RestController
@RequestMapping(path = "/cliente")
public class ClienteController {
	@Autowired
	ClienteService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteModel> buscarPorId(@PathVariable(required = true, value = "id") Long id) {
		ClienteModel obj = service.buscarPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(obj);
		}

}
