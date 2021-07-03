package org.rodrigo.cursomc.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public @Data @Builder @NoArgsConstructor @AllArgsConstructor class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

}
