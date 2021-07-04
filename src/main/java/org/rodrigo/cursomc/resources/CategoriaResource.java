package org.rodrigo.cursomc.resources;

import org.rodrigo.cursomc.domain.Categoria;
import org.rodrigo.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	CategoriaService service;

//	@GetMapping
//	public List<Categoria> listar() {
//		Categoria cat1;
//		Categoria cat2;
//
//		cat1 = new Categoria(1, "Informática");
//		cat2 = Categoria.builder().id(2).nome("Escritorio").build();
//
//		List<Categoria> listaCategoria = new ArrayList<>();
//		listaCategoria.add(cat1);
//		listaCategoria.add(cat2);
//
//		return listaCategoria;
//	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Categoria obj = service.buscar(id);

		return ResponseEntity.ok().body(obj);
	}

}
