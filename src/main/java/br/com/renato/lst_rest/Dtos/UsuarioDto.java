package br.com.renato.lst_rest.Dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioDto {
	@NotBlank(message = "Campo 'Nome' não pode estar vazio")
	@Size(min = 5, max = 200, message = "Campo 'Nome' deve ter entre {min} e {max} caracteres")
	private String nome;
	
	@NotBlank(message = "Campo 'Senha' não pode estar vazio")
	@Size(min = 5, max = 30, message = "Campo 'senha' deve ter entre {min} e {max} caracteres")
	private String senha;
	
	private Long idAdm;

	@NotBlank(message = "Campo 'Logon' não pode estar vazio")
	@Size(min = 5, max = 100, message = "Campo 'logon' deve ter entre {min} e {max} caracteres")
	private String logon;

}
