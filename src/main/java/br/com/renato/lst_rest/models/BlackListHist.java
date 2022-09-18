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
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tblst_restritiva_geral_hist")
public class BlackListHist implements Serializable {
	@Transient
	private static final long serialVersionUID = 1L;

	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@EqualsAndHashCode.Include()
	private ClienteModel cliente;

	@ManyToOne(cascade = CascadeType.REMOVE)
	private BlackList lstRestritiva;

	@Setter(AccessLevel.NONE)
	@Column(columnDefinition = "timestamp without time zone default CURRENT_TIMESTAMP")
	private Date dh;

	public BlackListHist(ClienteModel cliente, BlackList lstRestritiva) {
		super();
		this.cliente = cliente;
		this.lstRestritiva = lstRestritiva;

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
