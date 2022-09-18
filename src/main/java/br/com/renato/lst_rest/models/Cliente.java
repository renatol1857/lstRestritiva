package br.com.renato.lst_rest.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name = "tbcliente")
public class Cliente implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100, nullable = false, unique = true)
	private String nome;

	@Column(length = 80, nullable = true, unique = true)
	@EqualsAndHashCode.Include()
	private String alias;

	@Column(length = 500, nullable = true)
	private String descricao;

	@Column(columnDefinition = "smallint default 1 NOT NULL")
	private int status;

	@Setter(AccessLevel.NONE)
	@Column(columnDefinition = "timestamp without time zone default CURRENT_TIMESTAMP")
	private Date dh;

	public Cliente(String nome, String alias, String descricao, int status) {
		super();
		this.nome = nome;
		this.alias = alias;
		this.descricao = descricao;
		this.status = StatusEnum.DESATIVADO.getCod();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
