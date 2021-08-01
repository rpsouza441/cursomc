package org.rodrigo.cursomc.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.rodrigo.cursomc.domain.Pedido;
import org.rodrigo.cursomc.domain.Produto;
import org.rodrigo.cursomc.dto.ProdutoDTO;
import org.rodrigo.cursomc.resources.utils.URL;
import org.rodrigo.cursomc.services.ProdutoService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Produto obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}

	@PostMapping()
	public ResponseEntity<Void> insert(@Valid @RequestBody Produto prod,
			@RequestParam(name = "file") MultipartFile file) {
		prod = service.insert(prod);
		URI uri = gerarURI(prod);
		service.uploadPicture(file, prod.getId());
		return ResponseEntity.created(uri).build();
	}

	@PostMapping(value = "/image")
	public ResponseEntity<Void> insert(@Valid @RequestBody Produto prod) {
		prod = service.insert(prod);
		URI uri = gerarURI(prod);
		return ResponseEntity.created(uri).build();
	}

	@GetMapping()
	public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "nome", defaultValue = "0") String nome,
			@RequestParam(value = "listaCategoria", defaultValue = "0") String listaCategoria,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		List<Integer> ids = URL.decodeIntList(listaCategoria);
		String nomeDecoded = URL.decodeParam(nome);

		Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

	private URI gerarURI(Produto obj) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return uri;
	}

}
