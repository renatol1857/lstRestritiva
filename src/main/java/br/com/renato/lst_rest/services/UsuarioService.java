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
import br.com.renato.lst_rest.enumerator.AcaoEnum;
import br.com.renato.lst_rest.enumerator.PerfilEnum;
import br.com.renato.lst_rest.enumerator.StatusEnum;
import br.com.renato.lst_rest.models.Usuario;
import br.com.renato.lst_rest.models.UsuarioHist;
import br.com.renato.lst_rest.repositories.UsuarioHistRepository;
import br.com.renato.lst_rest.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repo;
	@Autowired
	private UsuarioHistRepository repoHist;
	
	
	public ResponseEntity<Object> buscarPorId (Long id) {
		Optional<Usuario> obj = repo.findById(id);
		if (obj.isEmpty()) {
			StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), String.format("Usuario não encontrado."));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
		return ResponseEntity.status(HttpStatus.OK).body(obj);
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

	private Usuario findAdm(Long id){
		Usuario objAdm = null;
		Optional<Usuario> optAdm = repo.findById(id);
		if (!optAdm.isEmpty())
			objAdm = optAdm.get();
		return objAdm;
	}
	
	private Integer validaCodPerfil(Integer cod) {
		Integer idPerfil = 1;
		if (PerfilEnum.isCodValid(cod))
			idPerfil = cod;
		return idPerfil;
	}
	
	private Integer validaCodStatus(Integer cod) {
		Integer idStatus = 1;
		if (StatusEnum.isCodValid(cod))
			idStatus = cod;
		return idStatus;
	}
	
	public ResponseEntity<Object> save(UsuarioDto userDto) {
		if (repo.existsByLogon(userDto.getLogon())) {
			StandardError error = new StandardError(HttpStatus.CONFLICT.value(), String.format("Conflito na criacao de usuário."));
			return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
		}
		
		Usuario objAdm = null;
		if (userDto.getIdAdm() instanceof Long)
			objAdm = findAdm(userDto.getIdAdm());
		
		Integer idPerfil = 1;
		if (userDto.getIdPerfil() instanceof Integer)
			idPerfil = validaCodPerfil(userDto.getIdPerfil());
		
		Integer idStaus = 1;
		if (userDto.getIdStatus() instanceof Integer) 
			idStaus = validaCodStatus(userDto.getIdStatus());
		
		Usuario user = new Usuario();
		BeanUtils.copyProperties(userDto, user);
		user.setPerfil(PerfilEnum.toEnum(idPerfil));
		user.setStatus(StatusEnum.toEnum(idStaus));
		Usuario obj = repo.save(user);
		UsuarioHist hist = new UsuarioHist(obj, objAdm,AcaoEnum.CREATE,"Criado");
		repoHist.save(hist);
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
	}

	public ResponseEntity<Object> update(Long id, UsuarioDto userDto) {
		Optional<Usuario> obj = repo.findById(id);
		if (obj.isEmpty()) {
			StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), String.format("Usuario não encontrado."));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
		Usuario user = obj.get();
		if (!user.getLogon().equals(userDto.getLogon())) {
			StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), String.format("Usuario nao pode alterar o logon."));
			return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
		}
		
		
		Usuario objAdm = null;
		if (userDto.getIdAdm() instanceof Long)
			objAdm = findAdm(userDto.getIdAdm());
		
		Integer idPerfil = 1;
		if (userDto.getIdPerfil() instanceof Integer)
			idPerfil = validaCodPerfil(userDto.getIdPerfil());
		
		Integer idStaus = 1;
		if (userDto.getIdStatus() instanceof Integer) 
			idStaus = validaCodStatus(userDto.getIdStatus());

		int icontAlteracoes = 0;
		StringBuilder strBuilder = new StringBuilder("Alterou");
		
		if (user.getPerfil().getCod() != idPerfil) {
			icontAlteracoes++;
			strBuilder.append(" Perfil [" + user.getPerfil().getDescricao() + " -> " + PerfilEnum.toEnum(idPerfil).getDescricao() + "]");
		}
		if (user.getStatus().getCod() != idStaus){
			icontAlteracoes++;
			strBuilder.append(" Status [" + user.getStatus().getDescricao() + " -> " + StatusEnum.toEnum(idPerfil).getDescricao() + "]");
		}
		if (!user.getNome().equals(userDto.getNome())){
			icontAlteracoes++;
			strBuilder.append(" Nome [" + user.getNome() + " -> " + userDto.getNome() + "]");
		}
		if (!user.isPswCorreto(userDto.getSenha())){
			icontAlteracoes++;
			strBuilder.append(" Senha [Foi alterda.]");
		}
		if (icontAlteracoes <= 0) {
			StandardError error = new StandardError(HttpStatus.OK.value(), String.format("Nao houve alteracoes ao usuario"));
			return ResponseEntity.status(HttpStatus.OK).body(error);
		}
			
		BeanUtils.copyProperties(userDto, user);
		user. setPerfil(PerfilEnum.toEnum(idPerfil));
		user.setStatus(StatusEnum.toEnum(idStaus));
		user = repo.save(user);
		UsuarioHist hist = new UsuarioHist(user, objAdm,AcaoEnum.UPDATE,strBuilder.toString());
		repoHist.save(hist);
		return ResponseEntity.status(HttpStatus.OK).body(obj);
	}
	

	
}
