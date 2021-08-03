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
import org.rodrigo.cursomc.domain.enums.Perfil;
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
		
		//infinite scroll
		Produto p12 = new Produto(null, "Produto 12", 10.00);
		Produto p13 = new Produto(null, "Produto 13", 10.00);
		Produto p14 = new Produto(null, "Produto 14", 10.00);
		Produto p15 = new Produto(null, "Produto 15", 10.00);
		Produto p16 = new Produto(null, "Produto 16", 10.00);
		Produto p17 = new Produto(null, "Produto 17", 10.00);
		Produto p18 = new Produto(null, "Produto 18", 10.00);
		Produto p19 = new Produto(null, "Produto 19", 10.00);
		Produto p20 = new Produto(null, "Produto 20", 10.00);
		Produto p21 = new Produto(null, "Produto 21", 10.00);
		Produto p22 = new Produto(null, "Produto 22", 10.00);
		Produto p23 = new Produto(null, "Produto 23", 10.00);
		Produto p24 = new Produto(null, "Produto 24", 10.00);
		Produto p25 = new Produto(null, "Produto 25", 10.00);
		Produto p26 = new Produto(null, "Produto 26", 10.00);
		Produto p27 = new Produto(null, "Produto 27", 10.00);
		Produto p28 = new Produto(null, "Produto 28", 10.00);
		Produto p29 = new Produto(null, "Produto 29", 10.00);
		Produto p30 = new Produto(null, "Produto 30", 10.00);
		Produto p31 = new Produto(null, "Produto 31", 10.00);
		Produto p32 = new Produto(null, "Produto 32", 10.00);
		Produto p33 = new Produto(null, "Produto 33", 10.00);
		Produto p34 = new Produto(null, "Produto 34", 10.00);
		Produto p35 = new Produto(null, "Produto 35", 10.00);
		Produto p36 = new Produto(null, "Produto 36", 10.00);
		Produto p37 = new Produto(null, "Produto 37", 10.00);
		Produto p38 = new Produto(null, "Produto 38", 10.00);
		Produto p39 = new Produto(null, "Produto 39", 10.00);
		Produto p40 = new Produto(null, "Produto 40", 10.00);
		Produto p41 = new Produto(null, "Produto 41", 10.00);
		Produto p42 = new Produto(null, "Produto 42", 10.00);
		Produto p43 = new Produto(null, "Produto 43", 10.00);
		Produto p44 = new Produto(null, "Produto 44", 10.00);
		Produto p45 = new Produto(null, "Produto 45", 10.00);
		Produto p46 = new Produto(null, "Produto 46", 10.00);
		Produto p47 = new Produto(null, "Produto 47", 10.00);
		Produto p48 = new Produto(null, "Produto 48", 10.00);
		Produto p49 = new Produto(null, "Produto 49", 10.00);
		Produto p50 = new Produto(null, "Produto 50", 10.00);

	
		cat1.getListaProduto().addAll(Arrays.asList(p1, p2, p3));
		cat2.getListaProduto().addAll(Arrays.asList(p2));
		
		
		//infinite scroll
		cat1.getListaProduto().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
				
		p12.getListaCategoria().add(cat1);
		p13.getListaCategoria().add(cat1);
		p14.getListaCategoria().add(cat1);
		p15.getListaCategoria().add(cat1);
		p16.getListaCategoria().add(cat1);
		p17.getListaCategoria().add(cat1);
		p18.getListaCategoria().add(cat1);
		p19.getListaCategoria().add(cat1);
		p20.getListaCategoria().add(cat1);
		p21.getListaCategoria().add(cat1);
		p22.getListaCategoria().add(cat1);
		p23.getListaCategoria().add(cat1);
		p24.getListaCategoria().add(cat1);
		p25.getListaCategoria().add(cat1);
		p26.getListaCategoria().add(cat1);
		p27.getListaCategoria().add(cat1);
		p28.getListaCategoria().add(cat1);
		p29.getListaCategoria().add(cat1);
		p30.getListaCategoria().add(cat1);
		p31.getListaCategoria().add(cat1);
		p32.getListaCategoria().add(cat1);
		p33.getListaCategoria().add(cat1);
		p34.getListaCategoria().add(cat1);
		p35.getListaCategoria().add(cat1);
		p36.getListaCategoria().add(cat1);
		p37.getListaCategoria().add(cat1);
		p38.getListaCategoria().add(cat1);
		p39.getListaCategoria().add(cat1);
		p40.getListaCategoria().add(cat1);
		p41.getListaCategoria().add(cat1);
		p42.getListaCategoria().add(cat1);
		p43.getListaCategoria().add(cat1);
		p44.getListaCategoria().add(cat1);
		p45.getListaCategoria().add(cat1);
		p46.getListaCategoria().add(cat1);
		p47.getListaCategoria().add(cat1);
		p48.getListaCategoria().add(cat1);
		p49.getListaCategoria().add(cat1);
		p50.getListaCategoria().add(cat1);	
		
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
				.senha(encoder.encode("123456")).cpf_cnpj("87299881012").tipo(TipoCliente.PESSOA_FISICA).build();
		cli1.getTelefones().addAll(Arrays.asList("99999 9999", "99999 8999"));

		Cliente cli2 = Cliente.builder().id(null).nome("Silva").email("rpsouza@gmail.com")
				.senha(encoder.encode("123456")).cpf_cnpj("70334020077").tipo(TipoCliente.PESSOA_FISICA).build();
		cli2.getTelefones().addAll(Arrays.asList("99992 9999", "99993 8999"));
		cli2.addPerfil(Perfil.ADMIN);

		Endereco e1 = Endereco.builder().id(null).logradouro("Rua Flores").numero("300").complemento("apt 303")
				.bairro("Jardim").cep("38262580").cliente(cli1).cidade(c1).build();
		Endereco e2 = Endereco.builder().id(null).logradouro("Avenida Marlos").numero("105").complemento("sala 800")
				.bairro("centro").cep("41262580").cliente(cli1).cidade(c2).build();
		Endereco e3 = Endereco.builder().id(null).logradouro("Avenida Marlos").numero("105").complemento("sala 800")
				.bairro("centro").cep("41262580").cliente(cli2).cidade(c2).build();

		cli1.getListEndereco().addAll((Arrays.asList(e1, e2)));
		cli2.getListEndereco().addAll((Arrays.asList(e3)));

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
		
		
		//infinite scroll
		prodRepo.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));



		estaRepo.saveAll(Arrays.asList(est1, est2));
		cidRepo.saveAll(Arrays.asList(c1, c2, c3));

		cliRepo.saveAll(Arrays.asList(cli1,cli2));
		endeRepo.saveAll(Arrays.asList(e1, e2,e3));

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
