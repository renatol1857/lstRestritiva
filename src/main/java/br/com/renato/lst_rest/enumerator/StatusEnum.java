package br.com.renato.lst_rest.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
	DESATIVADO(1,"Desativado"),
	ATIVADO(2,"Ativado"),
	PAUSA(3,"Pausa"),
	MANUTENCAO(4,"Manutencao"),
	DEMO(5,"Demonstracao");
	
	private int cod;
	private String descricao;
		
	
}
