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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tbMCDU_hist")
public class McduHist implements Serializable {
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Usuario adm;

	@ManyToOne
	@EqualsAndHashCode.Include()
	private Mcdu mcdu;

	@Column(length = 500, nullable = true)
	private String descricao;

	@Column(columnDefinition = "timestamp without time zone default CURRENT_TIMESTAMP")
	private Date dh;

	@Column(columnDefinition = "smallint default 1 NOT NULL")
	private Integer acao;

	public McduHist(Usuario adm, Mcdu mcdu, Integer acao, String descricao) {
		super();
		this.adm = adm;
		this.mcdu = mcdu;
		this.acao = acao;
		this.descricao = descricao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
