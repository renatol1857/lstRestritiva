package br.com.renato.lst_rest.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.renato.lst_rest.enumerator.StatusEnum;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tbdpto")
public class Departamento implements Serializable {
	@Transient
	@Setter(AccessLevel.NONE)
	private static final long serialVersionUID = 1L;
	
	@Setter(AccessLevel.NONE)
	private Usuario adm;

	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, nullable = false, unique = true)
	@Setter(AccessLevel.NONE)
	private String nome;

	@Column(length = 80, nullable = true, unique = true)
	@EqualsAndHashCode.Include()
	@Setter(AccessLevel.NONE)
	private String alias;

	@Column(length = 500, nullable = true)
	private String descricao;

	@Column(columnDefinition = "smallint default 1 NOT NULL")
	private int status;

	@Setter(AccessLevel.NONE)
	@Column(columnDefinition = "timestamp without time zone default CURRENT_TIMESTAMP")
	private Date dh;

	@ManyToOne()
	@EqualsAndHashCode.Include()
	private ClienteModel cliente;
	
	public Departamento(String nome, String alias, ClienteModel cliente, Usuario usuario, String descricao) {
		super();
		this.nome = nome;
		this.alias = alias;
		this.cliente = cliente;
		this.adm = usuario;
		this.descricao = descricao;
	}
	
	public StatusEnum getStatus() {
		return StatusEnum.toEnum(this.status);
	}

	public void setStatus(StatusEnum status) {
		this.status = status.getCod();
	}


	
}
