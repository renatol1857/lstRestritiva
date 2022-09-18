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
@Table(name = "tblista_restritiva")
public class BlackList implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 30, unique = true)
	@EqualsAndHashCode.Include()
	private String fullFone;

	@Column(length = 6)	
	private String ddd;

	@Column(length = 30)
	private String fone;

	@Setter(AccessLevel.NONE)
	@Column(columnDefinition = "timestamp without time zone default CURRENT_TIMESTAMP")
	private Date dh;

	public BlackList(String fullFone, String ddd, String fone) {
		super();
		this.fullFone = fullFone;
		this.ddd = ddd;
		this.fone = fone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	



}
