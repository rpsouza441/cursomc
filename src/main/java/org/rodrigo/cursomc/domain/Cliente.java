package org.rodrigo.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.rodrigo.cursomc.domain.enums.Perfil;
import org.rodrigo.cursomc.domain.enums.TipoCliente;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	private String nome;

	@Column(unique = true)
	private String email;
	@JsonIgnore
	private String senha;
	private String cpf_cnpj;
	private Integer tipo;
	

	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	@Builder.Default
	private Set<String> telefones = new HashSet<>();

	@Builder.Default
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	@Builder.Default
	private List<Endereco> listEndereco = new ArrayList<>();

	@JsonIgnore
	// @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Getter(onMethod = @__(@JsonIgnore))
	@OneToMany(mappedBy = "cliente")
	@Builder.Default
	private List<Pedido> listaPedido = new ArrayList<>();

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

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		if (perfis == null) {
			perfis = new HashSet<>();

		}
		perfis.add(perfil.getCod());
	}

	public Cliente() {
		addPerfil(Perfil.CLIENTE);

	}

	public Cliente(Integer id, String nome, String email, String senha, String cpf_cnpj, TipoCliente tipo) {
		super();
		addPerfil(Perfil.CLIENTE);
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.cpf_cnpj = cpf_cnpj;
		this.tipo = (tipo == null) ? null : tipo.getCod();
	}

	public Cliente(Integer id, String nome, String email, String senha, String cpf_cnpj, Integer tipo, 
			Set<String> telefones, Set<Integer> perfis, List<Endereco> listEndereco, List<Pedido> listaPedido) {
		super();
		this.id = id;
		addPerfil(Perfil.CLIENTE);
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.cpf_cnpj = cpf_cnpj;
		this.tipo = tipo;
		this.telefones = telefones;
		this.listEndereco = listEndereco;
		this.listaPedido = listaPedido;
	}

}
