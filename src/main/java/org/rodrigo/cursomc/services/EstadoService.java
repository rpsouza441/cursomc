package org.rodrigo.cursomc.services;

import java.util.List;

import org.rodrigo.cursomc.domain.Estado;
import org.rodrigo.cursomc.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;

	public List<Estado> findAll() {
		return repo.findAllByOrderByNome();
	}
}