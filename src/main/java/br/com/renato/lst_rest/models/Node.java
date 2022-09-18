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
@Table(name = "tbnode")
public class Node implements Serializable {
	@Transient
	private static final long serialVersionUID = 1L;

	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter(AccessLevel.NONE)
	@Column(columnDefinition = "smallint default 1 NOT NULL")
	@EqualsAndHashCode.Include()
	private Integer node;

	@Column(columnDefinition = "character varying(100) default 'Local' NOT NULL")
	private String nome;

	@Column(length = 500, nullable = true)
	private String descricao;

	@Setter(AccessLevel.NONE)
	@Column(columnDefinition = "timestamp without time zone default CURRENT_TIMESTAMP")
	private Date dh;

	public Node(String nome, String descricao) {
		super();
		this.node = getNumNode();
		this.nome = nome;
		this.descricao = descricao;
	}

	private int getNumNode() {
		return 1;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
