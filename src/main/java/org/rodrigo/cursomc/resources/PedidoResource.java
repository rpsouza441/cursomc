package org.rodrigo.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.rodrigo.cursomc.domain.Categoria;
import org.rodrigo.cursomc.domain.Pedido;
import org.rodrigo.cursomc.dto.CategoriaDTO;
import org.rodrigo.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private  PedidoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Pedido obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}
	

	@PostMapping()
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido pedido) {
		pedido = service.insert(pedido);
		URI uri = gerarURI(pedido);
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping()
	public ResponseEntity<Page<Pedido>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "instante") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		Page<Pedido> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
	private URI gerarURI(Pedido obj) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return uri;
	}

}
