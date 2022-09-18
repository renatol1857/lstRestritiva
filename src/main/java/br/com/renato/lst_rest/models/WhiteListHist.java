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
@Table(name = "tblista_liberada_hist")
public class WhiteListHist implements Serializable {
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.REMOVE)
	private Cliente cliente;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@EqualsAndHashCode.Include()
	private WhiteList lstGeral;

	@Column(columnDefinition = "timestamp without time zone default CURRENT_TIMESTAMP")
	private Date dh;

	public WhiteListHist(Cliente cliente, WhiteList lstGeral) {
		super();
		this.cliente = cliente;
		this.lstGeral = lstGeral;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
