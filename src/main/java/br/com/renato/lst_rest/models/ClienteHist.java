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

import br.com.renato.lst_rest.enumerator.AcaoEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tbcliente_hist")
public class ClienteHist implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 500, nullable = true)
	private String descricao;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@EqualsAndHashCode.Include()
	private ClienteModel cliente;

	@ManyToOne(cascade = CascadeType.REMOVE)
	private Usuario adm;

	@Column(columnDefinition = "timestamp without time zone default CURRENT_TIMESTAMP")
	private Date dh;

	@Column(columnDefinition = "smallint default 1 NOT NULL")
	private Integer acao;

	public ClienteHist() {
		super();
	}

	public ClienteHist(Usuario adm, ClienteModel cliente, AcaoEnum acao, String descricao) {
		super();
		this.adm = adm;
		this.cliente = cliente;
		this.acao = acao.getCod();
		this.descricao = descricao;
	}

	public AcaoEnum getStatus() {
		return AcaoEnum.toEnum(this.acao);
	}

	public void setStatus(AcaoEnum status) {
		this.acao = status.getCod();
	}


}
