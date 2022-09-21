package br.com.renato.lst_rest.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.renato.lst_rest.enumerator.PerfilEnum;
import br.com.renato.lst_rest.enumerator.StatusEnum;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tbuser")
public class Usuario implements Serializable {
	@Transient
	@Setter(AccessLevel.NONE)
	private static final long serialVersionUID = 1L;

	// private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Long id;

	@Column(length = 200, nullable = false)
	private String nome;

	@Column(length = 100, nullable = false, unique = true)
	@EqualsAndHashCode.Include()
	private String logon;

	@Getter(AccessLevel.NONE)
	@Column(length = 30, nullable = false)
	private String senha;

	@Column(nullable = false)
	private int status=1;
	
	@Column(nullable = false)
	private int perfil=1;

	public Usuario(String nome, String logon, String senha) {
		super();
		this.nome = nome;
		this.logon = logon;
		this.senha = senha;
		}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PerfilEnum getPerfil() {
		return PerfilEnum.toEnum(this.perfil);
	}
	
	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil.getCod();
	}
	
	public StatusEnum getStatus() {
		return StatusEnum.toEnum(this.status);
	}
	
	public void setStatus(StatusEnum status) {
		this.status = status.getCod();
	}

	



}
