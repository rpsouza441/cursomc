package org.rodrigo.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.rodrigo.cursomc.domain.Cliente;
import org.rodrigo.cursomc.dto.ClienteDTO;
import org.rodrigo.cursomc.dto.ClienteNewDTO;
import org.rodrigo.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Cliente obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}

	@PostMapping()
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDTO) {
		Cliente obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = gerarURI(obj);
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> put(@Valid @RequestBody ClienteDTO dto, @PathVariable Integer id) {
		Cliente obj = service.fromDTO(dto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping()
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

	private URI gerarURI(Cliente obj) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return uri;
	}
}
