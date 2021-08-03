package org.rodrigo.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	private @NonNull String nome;
	private @NonNull Double preco;

	@JsonIgnore
    //@Getter(onMethod = @__( @JsonIgnore ))
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private @Builder.Default List<Categoria> listaCategoria = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "id.produto")
	private @Builder.Default Set<ItemPedido> listaItemPedido = new HashSet<>();

	@JsonIgnore
	public List<Pedido> getListaPedidos() {
		List<Pedido> lista = new ArrayList<>();
		listaItemPedido.forEach(x -> lista.add(x.getPedido()));
		return lista;
	}

	@JsonIgnore
	public List<Categoria> getListaCategoria() {
		if (this.listaCategoria==null) {
			this.listaCategoria = new ArrayList<>();
		}
		return listaCategoria;
	}

	public Produto(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	
	

}
