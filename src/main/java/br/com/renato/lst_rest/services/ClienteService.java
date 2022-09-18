package br.com.renato.lst_rest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renato.lst_rest.models.ClienteModel;
import br.com.renato.lst_rest.repositories.ClienteRepository;
import br.com.renato.lst_rest.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public ClienteModel save(ClienteModel cliente) {
		return repo.save(cliente);
	}

	public ClienteModel buscarPorId(Long id) {
		Optional<ClienteModel> obj = repo.findById (id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(String.format("Categoria nao encontrada ID [%d] Tipo %s", id, ClienteModel.class.getName())));
	}
	
}
