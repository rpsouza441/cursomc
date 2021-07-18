package org.rodrigo.cursomc.repositories;

import java.util.List;

import org.rodrigo.cursomc.domain.Categoria;
import org.rodrigo.cursomc.domain.Produto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.listaCategoria cat WHERE obj.nome LIKE %:nome% AND cat IN :listaCategoria")
	Page<Produto> search(@Param("nome") String nome, @Param("listaCategoria") List<Categoria> listaCategoria, Pageable p);

	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.listaCategoria cat WHERE obj.nome LIKE %:nome% AND cat IN :listaCategoria")
	Page<Produto> findDistinctByNomeContainingAndListaCategoriaIn(@Param("nome") String nome,
			@Param("listaCategoria") List<Categoria> listaCategoria, Pageable pageRequest);

}
