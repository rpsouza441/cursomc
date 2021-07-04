package org.rodrigo.cursomc.domain.enums;

import lombok.Getter;

@Getter
public enum TipoCliente {

	PESSOA_FISICA(1, "Pessoa Física"), PESSOA_JURIDICA(2, "Pessoa Jurídica");

	private int cod;
	private String descricao;

	private TipoCliente(int cod, String descricao) {
		this.cod = cod; 
		this.descricao = descricao;
	}

	public static TipoCliente toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TipoCliente x : TipoCliente.values()) {
			if (cod.equals(x.getCod())) {
				return x;

			}
		}
		throw new IllegalArgumentException("Id Inválido" + cod);
	}
}
