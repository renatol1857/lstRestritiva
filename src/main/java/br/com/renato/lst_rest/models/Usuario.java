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
	private static final long serialVersionUID = 1L;

	// private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 200, nullable = false)
	private String nome;

	@Setter(AccessLevel.NONE)
	@ManyToOne
	private Usuario adm;

	@Column(length = 100, nullable = false, unique = true)
	@EqualsAndHashCode.Include()
	private String logon;

	@Getter(AccessLevel.PACKAGE)
	@Column(length = 100, nullable = false)
	private String senha;

	@Column(columnDefinition = "smallint default 1 NOT NULL")
	private int status;

	public Usuario(String nome, String logon, String senha) {
		super();
		this.nome = nome;
		this.logon = logon;
		this.senha = senha;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
