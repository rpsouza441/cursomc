package org.rodrigo.cursomc;

import java.util.Arrays;

import org.rodrigo.cursomc.domain.Categoria;
import org.rodrigo.cursomc.domain.Cidade;
import org.rodrigo.cursomc.domain.Estado;
import org.rodrigo.cursomc.domain.Produto;
import org.rodrigo.cursomc.repositories.CategoriaRepository;
import org.rodrigo.cursomc.repositories.CidadeRepository;
import org.rodrigo.cursomc.repositories.EstadoRepository;
import org.rodrigo.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication  implements CommandLineRunner{

	@Autowired
	private CategoriaRepository catRepo;
	@Autowired
	private ProdutoRepository prodRepo;
	@Autowired
	private CidadeRepository cidRepo;
	@Autowired
	private EstadoRepository estaRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
				
		Categoria cat1 = Categoria.builder().id(null).nome("Informatica").build();
		Categoria cat2 = Categoria.builder().id(null).nome("Escritorio").build();
		Produto p1 = Produto.builder().id(null).nome("computador").preco(2000.0).build();
		Produto p2 = Produto.builder().id(null).nome("Impressora").preco(800.0).build();
		Produto p3 = Produto.builder().id(null).nome("Mouse").preco(80.0).build();
		
		cat1.getListaProduto().addAll(Arrays.asList(p1,p2,p3));
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
		
		
		catRepo.saveAll(Arrays.asList(cat1,cat2));
		prodRepo.saveAll(Arrays.asList(p1,p2,p3));
		
		estaRepo.saveAll(Arrays.asList(est1,est2));
		cidRepo.saveAll(Arrays.asList(c1,c2,c3));
		
		
	}

}
