package br.com.renato.lst_rest.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tbnode_hist")
public class NodeHist implements Serializable {
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.REMOVE)
	private Usuario adm;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@EqualsAndHashCode.Include()
	private Node node;

	@Column(columnDefinition = "timestamp without time zone default CURRENT_TIMESTAMP")
	private Date dh;

	@Column(columnDefinition = "smallint default 1 NOT NULL")
	private Integer acao;

	@Column(length = 500, nullable = true)
	private String descricao;

	public NodeHist(Usuario adm, Node node, Integer acao, String descricao) {
		super();
		this.adm = adm;
		this.node = node;
		this.acao = acao;
		this.descricao = descricao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
