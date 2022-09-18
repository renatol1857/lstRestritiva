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
@Table(name = "tbpadrao")
public class Padrao implements Serializable {
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "smallint default 1 NOT NULL")
	private int tipo;

	@Column(length = 50, nullable = false)
	private String padrao;

	@Column(columnDefinition = "smallint default 1 NOT NULL")
	private int status;

	@Column(length = 500, nullable = true)
	private String descricao;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@EqualsAndHashCode.Include()
	private Mcdu mcdu;

	@Column(columnDefinition = "timestamp without time zone default CURRENT_TIMESTAMP")
	private Date dh;

	public Padrao(Mcdu mcdu, int tipo, String padrao, String descricao) {
		super();
		this.mcdu = mcdu;
		this.tipo = tipo;
		this.padrao = padrao;
		this.descricao = descricao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
