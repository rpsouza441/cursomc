package org.rodrigo.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.rodrigo.cursomc.domain.enums.TipoCliente;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
	private Integer id;
	private String nome;
	private String email;
	private String cpf_cnpj;
	private Integer tipo;

	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	@Builder.Default
	private Set<String> telefones = new HashSet<>();

	@JsonManagedReference
	@OneToMany(mappedBy = "cliente")
	@Builder.Default
	private List<Endereco> listEndereco = new ArrayList<>();
	
	@JsonBackReference
	@OneToMany(mappedBy = "cliente")
	@Builder.Default
	private List<Pedido> listaPedido = new ArrayList<>();

	public Cliente(Integer id, String nome, String email, String cpf_cnpj, TipoCliente tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf_cnpj = cpf_cnpj;
		this.tipo = tipo.getCod();
	}

	public static class ClienteBuilder {
		public ClienteBuilder tipo(TipoCliente tipo) {
			this.tipo = tipo.getCod();
			return this;
		}
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}

	public Cliente(Integer id, String nome, String email, String cpf_cnpj, Integer tipo, Set<String> telefones,
			List<Endereco> listEndereco, List<Pedido> listaPedido) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf_cnpj = cpf_cnpj;
		this.tipo = tipo;
		this.telefones = telefones;
		this.listEndereco = listEndereco;
		this.listaPedido = listaPedido;
	}


}
