package org.rodrigo.cursomc.domain;

import javax.persistence.Entity;

import org.rodrigo.cursomc.domain.enums.EstadoPagamento;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;
	private Integer numeroDeParcelas;

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public PagamentoComCartao() {
		super();
	}

}
