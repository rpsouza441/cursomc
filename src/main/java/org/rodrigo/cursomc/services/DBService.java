package org.rodrigo.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.rodrigo.cursomc.domain.Categoria;
import org.rodrigo.cursomc.domain.Cidade;
import org.rodrigo.cursomc.domain.Cliente;
import org.rodrigo.cursomc.domain.Endereco;
import org.rodrigo.cursomc.domain.Estado;
import org.rodrigo.cursomc.domain.ItemPedido;
import org.rodrigo.cursomc.domain.Pagamento;
import org.rodrigo.cursomc.domain.PagamentoComBoleto;
import org.rodrigo.cursomc.domain.PagamentoComCartao;
import org.rodrigo.cursomc.domain.Pedido;
import org.rodrigo.cursomc.domain.Produto;
import org.rodrigo.cursomc.domain.enums.EstadoPagamento;
import org.rodrigo.cursomc.domain.enums.TipoCliente;
import org.rodrigo.cursomc.repositories.CategoriaRepository;
import org.rodrigo.cursomc.repositories.CidadeRepository;
import org.rodrigo.cursomc.repositories.ClienteRepository;
import org.rodrigo.cursomc.repositories.EnderecoRepository;
import org.rodrigo.cursomc.repositories.EstadoRepository;
import org.rodrigo.cursomc.repositories.ItemPedidoRepository;
import org.rodrigo.cursomc.repositories.PagamentoRepository;
import org.rodrigo.cursomc.repositories.PedidoRepository;
import org.rodrigo.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class DBService {
	@Autowired
	private CategoriaRepository catRepo;
	@Autowired
	private ProdutoRepository prodRepo;
	@Autowired
	private CidadeRepository cidRepo;
	@Autowired
	private EstadoRepository estaRepo;
	@Autowired
	private ClienteRepository cliRepo;
	@Autowired
	private EnderecoRepository endeRepo;
	@Autowired
	private PedidoRepository pedidoRepo;
	@Autowired
	private PagamentoRepository pagamentoRepo;
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public void instantiateTestDatabase() throws ParseException {

		Categoria cat1 = Categoria.builder().id(null).nome("Informatica").build();
		Categoria cat2 = Categoria.builder().id(null).nome("Escritorio").build();
		Categoria cat3 = Categoria.builder().id(null).nome("Cama mesa e banho").build();
		Categoria cat4 = Categoria.builder().id(null).nome("Eletrônicos").build();
		Categoria cat5 = Categoria.builder().id(null).nome("Jardinagem").build();
		Categoria cat6 = Categoria.builder().id(null).nome("Decoração").build();
		Categoria cat7 = Categoria.builder().id(null).nome("Perfumaria").build();

		Produto p1 = Produto.builder().id(null).nome("computador").preco(2000.0).build();
		Produto p2 = Produto.builder().id(null).nome("Impressora").preco(800.0).build();
		Produto p3 = Produto.builder().id(null).nome("Mouse").preco(80.0).build();
		Produto p4 = Produto.builder().id(null).nome("Mesa de ecritorio").preco(300.0).build();
		Produto p5 = Produto.builder().id(null).nome("Toalha").preco(50.0).build();
		Produto p6 = Produto.builder().id(null).nome("Colcha").preco(200.0).build();
		Produto p7 = Produto.builder().id(null).nome("TV").preco(1200.0).build();
		Produto p8 = Produto.builder().id(null).nome("Roçadeira").preco(800.0).build();
		Produto p9 = Produto.builder().id(null).nome("Abajour").preco(100.0).build();
		Produto p10 = Produto.builder().id(null).nome("Pendente").preco(180.0).build();
		Produto p11 = Produto.builder().id(null).nome("Shampoo").preco(90.0).build();
		cat1.getListaProduto().addAll(Arrays.asList(p1, p2, p3));
		cat2.getListaProduto().addAll(Arrays.asList(p2));

		p1.getListaCategoria().addAll(Arrays.asList(cat1));
		p2.getListaCategoria().addAll(Arrays.asList(cat1, cat2));
		p3.getListaCategoria().addAll(Arrays.asList(cat1));

		cat2.getListaProduto().addAll(Arrays.asList(p2, p4));
		cat3.getListaProduto().addAll(Arrays.asList(p5, p6));
		cat4.getListaProduto().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getListaProduto().addAll(Arrays.asList(p8));
		cat6.getListaProduto().addAll(Arrays.asList(p9, p10));
		cat7.getListaProduto().addAll(Arrays.asList(p11));

		p1.getListaCategoria().addAll(Arrays.asList(cat1, cat4));
		p2.getListaCategoria().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getListaCategoria().addAll(Arrays.asList(cat1, cat4));
		p4.getListaCategoria().addAll(Arrays.asList(cat2));
		p5.getListaCategoria().addAll(Arrays.asList(cat3));
		p6.getListaCategoria().addAll(Arrays.asList(cat3));
		p7.getListaCategoria().addAll(Arrays.asList(cat4));
		p8.getListaCategoria().addAll(Arrays.asList(cat5));
		p9.getListaCategoria().addAll(Arrays.asList(cat6));
		p10.getListaCategoria().addAll(Arrays.asList(cat6));
		p11.getListaCategoria().addAll(Arrays.asList(cat7));

		Estado est1 = Estado.builder().id(null).nome("Minas Gerais").build();
		Estado est2 = Estado.builder().id(null).nome("Sao Paulo").build();

		Cidade c1 = Cidade.builder().id(null).nome("Uberlandia").estado(est1).build();
		Cidade c2 = Cidade.builder().id(null).nome("Sao Paulo").estado(est2).build();
		Cidade c3 = Cidade.builder().id(null).nome("Campinas").estado(est2).build();

		est1.getListCidade().addAll((Arrays.asList(c1)));
		est2.getListCidade().addAll((Arrays.asList(c2, c3)));

		Cliente cli1 = Cliente.builder().id(null).nome("Maria Silva").email("rpsouzati@gmail.com")
				.senha(encoder.encode("123456"))
				.cpf_cnpj("872.998.810-12").tipo(TipoCliente.PESSOA_FISICA).build();
		cli1.getTelefones().addAll(Arrays.asList("99999 9999", "99999 8999"));

		Endereco e1 = Endereco.builder().id(null).logradouro("Rua Flores").numero("300").complemento("apt 303")
				.bairro("Jardim").cep("38262580").cliente(cli1).cidade(c1).build();
		Endereco e2 = Endereco.builder().id(null).logradouro("Avenida Marlos").numero("105").complemento("sala 800")
				.bairro("centro").cep("41262580").cliente(cli1).cidade(c2).build();

		cli1.getListEndereco().addAll((Arrays.asList(e1, e2)));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = Pedido.builder().id(null).instante(sdf.parse("30/09/2017 10:32")).cliente(cli1)
				.enderecoDeEntrega(e1).build();
		Pedido ped2 = Pedido.builder().id(null).instante(sdf.parse("10/10/2017 19:35")).cliente(cli1)
				.enderecoDeEntrega(e2).build();

		Pagamento pagto1 = PagamentoComCartao.builder().id(null).estado(EstadoPagamento.QUITADO.getCod()).pedido(ped1)
				.numeroDeParcelas(6).build();
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = PagamentoComBoleto.builder().id(null).estado(EstadoPagamento.PENDENTE.getCod()).pedido(ped2)
				.dataVencimento(sdf.parse("20/10/2017 00:00")).dataPagamento(null).build();
		ped2.setPagamento(pagto2);

		cli1.getListaPedido().addAll((Arrays.asList(ped1, ped2)));

		catRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		prodRepo.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		estaRepo.saveAll(Arrays.asList(est1, est2));
		cidRepo.saveAll(Arrays.asList(c1, c2, c3));

		cliRepo.saveAll(Arrays.asList(cli1));
		endeRepo.saveAll(Arrays.asList(e1, e2));

		pedidoRepo.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepo.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = ItemPedido.builder().ItemPedidoPK(ped1, p1).desconto(0.00).quantidade(1).preco(2000.00)
				.build();
		ItemPedido ip2 = ItemPedido.builder().ItemPedidoPK(ped1, p3).desconto(0.00).quantidade(2).preco(80.00).build();
		ItemPedido ip3 = ItemPedido.builder().ItemPedidoPK(ped2, p2).desconto(100.00).quantidade(1).preco(800.00)
				.build();

		ped1.getListaItemPedido().addAll((Arrays.asList(ip1, ip2)));
		ped2.getListaItemPedido().addAll((Arrays.asList(ip3)));

		p1.getListaItemPedido().addAll((Arrays.asList(ip1)));
		p2.getListaItemPedido().addAll((Arrays.asList(ip3)));
		p3.getListaItemPedido().addAll((Arrays.asList(ip2)));

		itemPedidoRepo.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
