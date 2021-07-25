package org.rodrigo.cursomc.services;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.rodrigo.cursomc.domain.ItemPedido;
import org.rodrigo.cursomc.domain.PagamentoComBoleto;
import org.rodrigo.cursomc.domain.Pedido;
import org.rodrigo.cursomc.domain.enums.EstadoPagamento;
import org.rodrigo.cursomc.repositories.ItemPedidoRepository;
import org.rodrigo.cursomc.repositories.PagamentoRepository;
import org.rodrigo.cursomc.repositories.PedidoRepository;
import org.rodrigo.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private PagamentoRepository pagamentoRepo;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteService clienteService;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public Pedido insert(@Valid Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.setCliente(clienteService.find(pedido.getCliente().getId()));
		
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if (pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());
		}
		pedido = repo.save(pedido);
		pagamentoRepo.save(pedido.getPagamento());
		for (ItemPedido ip : pedido.getListaItemPedido()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(pedido);
		}
		itemPedidoRepository.saveAll(pedido.getListaItemPedido());
		System.out.println(pedido);
		return pedido;
	}
}
