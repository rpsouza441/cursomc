package org.rodrigo.cursomc.services;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.rodrigo.cursomc.domain.Cliente;
import org.rodrigo.cursomc.domain.ItemPedido;
import org.rodrigo.cursomc.domain.PagamentoComBoleto;
import org.rodrigo.cursomc.domain.Pedido;
import org.rodrigo.cursomc.domain.enums.EstadoPagamento;
import org.rodrigo.cursomc.repositories.ItemPedidoRepository;
import org.rodrigo.cursomc.repositories.PagamentoRepository;
import org.rodrigo.cursomc.repositories.PedidoRepository;
import org.rodrigo.cursomc.security.UserSS;
import org.rodrigo.cursomc.services.exception.AuthorizationException;
import org.rodrigo.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
	
	@Autowired
	private EmailService emailService;

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
		//System.out.println(pedido);
		emailService.sendOrderConfirmationHtmlEmail(pedido);
		return pedido;
	}
	
	
	
	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS u = UserService.authenticated();
		if (u== null) {
			throw new AuthorizationException("Acesso Negado");
		}
		PageRequest p = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Cliente c = clienteService.find(u.getId());
		
		return repo.findByCliente(c, p);

	}
}
