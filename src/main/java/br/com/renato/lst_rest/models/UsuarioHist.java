package br.com.renato.lst_rest.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.renato.lst_rest.enumerator.AcaoEnum;
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
@Table(name = "tbuser_hist")
public class UsuarioHist  implements Serializable {
	@Transient
	@Setter(AccessLevel.NONE)
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Long id;

	@OneToOne
	@EqualsAndHashCode.Include()
	private Usuario user;

	@OneToOne
	private Usuario adm;

	@Column(length = 500, nullable = true)
	private String descricao;

	@Column(nullable = false)
	private Date dh = new Date();

	@Column(nullable = false)
	private Integer acao=1;

	public UsuarioHist(Usuario user, Usuario adm, AcaoEnum acao, String descricao) {
		super();
		this.user = user;
		this.adm = adm;
		this.acao = acao.getCod();
		this.descricao = descricao;
	}

	public AcaoEnum getAcao() {
		return AcaoEnum.toEnum(this.acao);
	}
	
	public void setAcao(AcaoEnum status) {
		this.acao = status.getCod();
	}	
	
	
	
}
