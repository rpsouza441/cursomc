package org.rodrigo.cursomc;

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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

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

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = Categoria.builder().id(null).nome("Informatica").build();
		Categoria cat2 = Categoria.builder().id(null).nome("Escritorio").build();
		Categoria cat3= Categoria.builder().id(null).nome("Cama mesa e banho").build();
		Categoria cat4 = Categoria.builder().id(null).nome("Eletrônicos").build();
		Categoria cat5 = Categoria.builder().id(null).nome("Jardinagem").build();
		Categoria cat6 = Categoria.builder().id(null).nome("Decoração").build();
		Categoria cat7 = Categoria.builder().id(null).nome("Perfumaria").build();
		
		
		Produto p1 = Produto.builder().id(null).nome("computador").preco(2000.0).build();
		Produto p2 = Produto.builder().id(null).nome("Impressora").preco(800.0).build();
		Produto p3 = Produto.builder().id(null).nome("Mouse").preco(80.0).build();

		cat1.getListaProduto().addAll(Arrays.asList(p1, p2, p3));
		cat1.getListaProduto().addAll(Arrays.asList(p2));

		p1.getListaCategoria().addAll(Arrays.asList(cat1));
		p2.getListaCategoria().addAll(Arrays.asList(cat1, cat2));
		p3.getListaCategoria().addAll(Arrays.asList(cat1));

		Estado est1 = Estado.builder().id(null).nome("Minas Gerais").build();
		Estado est2 = Estado.builder().id(null).nome("Sao Paulo").build();

		Cidade c1 = Cidade.builder().id(null).nome("Uberlandia").estado(est1).build();
		Cidade c2 = Cidade.builder().id(null).nome("Sao Paulo").estado(est2).build();
		Cidade c3 = Cidade.builder().id(null).nome("Campinas").estado(est2).build();

		est1.getListCidade().addAll((Arrays.asList(c1)));
		est2.getListCidade().addAll((Arrays.asList(c2, c3)));

		Cliente cli1 = Cliente.builder().id(null).nome("Maria Silva").email("maria@gmail.com")
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

		catRepo.saveAll(Arrays.asList(cat1, cat2,cat3,cat4,cat5,cat6,cat7));
		prodRepo.saveAll(Arrays.asList(p1, p2, p3));

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
