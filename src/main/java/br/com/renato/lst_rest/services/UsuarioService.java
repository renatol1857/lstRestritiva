package br.com.renato.lst_rest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renato.lst_rest.models.Usuario;
import br.com.renato.lst_rest.repositories.UsuarioRepository;
import br.com.renato.lst_rest.services.exception.ObjectNotFoundException;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repo;
	
	public Usuario BuscarPorId (Long id) {
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException (String.format("Usuário não encontrada ID [%d] em [%s]", id, UsuarioService.class.getSimpleName())));
	}

}
