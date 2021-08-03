package org.rodrigo.cursomc.dto;

import java.io.Serializable;

import org.rodrigo.cursomc.domain.Estado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;

	public EstadoDTO(Estado obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

}
