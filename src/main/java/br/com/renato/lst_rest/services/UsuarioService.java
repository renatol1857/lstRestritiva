package br.com.renato.lst_rest.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renato.lst_rest.Dtos.UsuarioDto;
import br.com.renato.lst_rest.controllers.exception.StandardError;
import br.com.renato.lst_rest.models.Usuario;
import br.com.renato.lst_rest.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repo;
	
	public ResponseEntity<Object> buscarPorId (Long id) {
		Optional<Usuario> obj = repo.findById(id);
		if (obj.isEmpty()) {
			StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), String.format("Usuario não encontrado."));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
		return ResponseEntity.status(HttpStatus.OK).body(obj);
	}

	public ResponseEntity<Object> save(UsuarioDto userDto) {
		if (repo.existsByLogon(userDto.getLogon())) {
			StandardError error = new StandardError(HttpStatus.CONFLICT.value(), String.format("Conflito na criacao de usuário."));
			return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
		}
		Usuario user = new Usuario();
		BeanUtils.copyProperties(userDto, user);
		Usuario obj = repo.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
	}

	public ResponseEntity<Object> save(Map<String, Object> dicDados) {
		if (repo.existsByLogon(dicDados.get("logon").toString())) {
			StandardError error = new StandardError(HttpStatus.CONFLICT.value(), String.format("Conflito na criacao de usuário."));
			return ResponseEntity.status(HttpStatus.CREATED).body(error);
		}
		if ( dicDados.get("senha").toString() == null || dicDados.get("senha").toString().length() < 3 ) {
			StandardError error = new StandardError(HttpStatus.CONFLICT.value(), String.format("Senha nao pode ser em branco."));
			return ResponseEntity.status(HttpStatus.CREATED).body(error);
		}
		Usuario user = new Usuario(dicDados.get("nome").toString(),dicDados.get("logon").toString(),dicDados.get("senha").toString());
		Usuario obj = repo.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
	}

}
