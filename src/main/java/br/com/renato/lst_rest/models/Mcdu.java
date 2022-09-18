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
@Table(name = "tbmcdu")
public class Mcdu implements Serializable {
	@Transient
	private static final long serialVersionUID = 1L;

	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter(AccessLevel.NONE)
	@Column(length = 20, unique = false)
	@EqualsAndHashCode.Include()
	private String mcdu;

	@Column(length = 120, nullable = false)
	private String nome;

	@ManyToOne(cascade = CascadeType.REMOVE)
	private Usuario adm;

	@Column(length = 60, nullable = true)
	private String alias;

	@Column(length = 500, nullable = true)
	private String descricao;

	@Column(columnDefinition = "smallint default 1 NOT NULL")
	private int status;

	@Setter(AccessLevel.NONE)
	@Column(columnDefinition = "smallint default 1 NOT NULL")
	private int idxTabela;

	@Column(columnDefinition = "smallint default 20 NOT NULL")
	private int maxAcesso;

	@Column(columnDefinition = "smallint default 5 NOT NULL")
	private int maxInfo;

	@Column(columnDefinition = "character varying(20) default '1|0|0' NOT NULL")
	private String deltaTemp;

	@Column(columnDefinition = "boolean default false NOT NULL")
	private Boolean flagCriarBLst;

	@Column(columnDefinition = "boolean default false NOT NULL")
	private Boolean flagUpAutoBLst;

	@Column(columnDefinition = "smallint default 200 NOT NULL")
	private int maxAcessoBLst;

	@Setter(AccessLevel.NONE)
	@Column(columnDefinition = "timestamp without time zone default CURRENT_TIMESTAMP")
	private Date dh;

	public Mcdu(Usuario adm, String mcdu, String nome, String alias, String descricao) {
		super();
		this.adm = adm;
		this.mcdu = mcdu;
		this.nome = nome;
		this.alias = alias;
		this.descricao = descricao;
		this.idxTabela = criarIDX();
	}

	private int criarIDX() {
		return 1;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
