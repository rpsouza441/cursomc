package org.rodrigo.cursomc.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.rodrigo.cursomc.domain.Cidade;
import org.rodrigo.cursomc.domain.Cliente;
import org.rodrigo.cursomc.domain.Endereco;
import org.rodrigo.cursomc.domain.enums.Perfil;
import org.rodrigo.cursomc.domain.enums.TipoCliente;
import org.rodrigo.cursomc.dto.ClienteDTO;
import org.rodrigo.cursomc.dto.ClienteNewDTO;
import org.rodrigo.cursomc.repositories.ClienteRepository;
import org.rodrigo.cursomc.repositories.EnderecoRepository;
import org.rodrigo.cursomc.security.UserSS;
import org.rodrigo.cursomc.services.exception.AuthorizationException;
import org.rodrigo.cursomc.services.exception.DataIntegrityException;
import org.rodrigo.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepo;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private S3Service s3;

	public Cliente find(Integer id) {
		UserSS u = UserService.authenticated();

		if (u != null && !u.hasRole(Perfil.ADMIN) && !id.equals((u.getId()))) {
			throw new AuthorizationException("Acesso negado");

		}

		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepo.saveAll(obj.getListEndereco());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma cliente que possui pedidos");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest p = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(p);

	}

	public URI uploadProfilePicture(MultipartFile mf) {
		return s3.uploadFile(mf);

	}

	public Cliente fromDTO(ClienteDTO dto) {
		return Cliente.builder().id(dto.getId()).nome(dto.getNome()).email(dto.getEmail()).build();
	}

	public Cliente fromDTO(ClienteNewDTO dto) {
		Cliente cli = Cliente.builder().id(null).nome(dto.getNome()).email(dto.getEmail())
				.senha(encoder.encode(dto.getSenha())).cpf_cnpj(dto.getCpf_cnpj())
				.tipo(TipoCliente.toEnum(dto.getTipo())).build();

		Cidade c = Cidade.builder().id(dto.getCidadeId()).build();

		Endereco e = Endereco.builder().logradouro(dto.getLogradouro()).numero(dto.getNumero())
				.complemento(dto.getComplemento()).bairro(dto.getBairro()).cep(dto.getCep()).cliente(cli).cidade(c)
				.build();

		cli.getListEndereco().add(e);

		confereSeExitePreencheTelefones(dto, cli);

		return cli;
	}

	private void confereSeExitePreencheTelefones(ClienteNewDTO dto, Cliente cli) {
		if (dto.getTelefone1() != null) {
			cli.getTelefones().add(dto.getTelefone1());
		}
		if (dto.getTelefone2() != null) {
			cli.getTelefones().add(dto.getTelefone2());
		}
		if (dto.getTelefone3() != null) {
			cli.getTelefones().add(dto.getTelefone3());
		}
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
