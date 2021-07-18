package org.rodrigo.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.rodrigo.cursomc.domain.Categoria;
import org.rodrigo.cursomc.domain.Produto;
import org.rodrigo.cursomc.repositories.CategoriaRepository;
import org.rodrigo.cursomc.repositories.ProdutoRepository;
import org.rodrigo.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;

	@Autowired
	private CategoriaRepository categoriaRepo;

	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest p = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> listaCategorias = categoriaRepo.findAllById(ids);
		return repo.search(nome, listaCategorias, p);

	}

}
