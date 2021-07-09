package org.rodrigo.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import org.rodrigo.cursomc.domain.enums.EstadoPagamento;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PagamentoComBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;

	private Date dataVencimento;
	private Date dataPagamento;

	public PagamentoComBoleto() {
		super();
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento,
			Date dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

}
