package org.rodrigo.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.rodrigo.cursomc.domain.Categoria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@GetMapping
	public List<Categoria> listar() {
		Categoria cat1;
		Categoria cat2;

		cat1 = new Categoria(1, "Informática");
		cat2 = Categoria.builder().id(2).nome("Escritorio").build();

		List<Categoria> listaCategoria = new ArrayList<>();
		listaCategoria.add(cat1);
		listaCategoria.add(cat2);

		return listaCategoria;
	}

}
