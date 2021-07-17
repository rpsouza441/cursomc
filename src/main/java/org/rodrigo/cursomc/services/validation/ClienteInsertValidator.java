package org.rodrigo.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.rodrigo.cursomc.domain.Cliente;
import org.rodrigo.cursomc.domain.enums.TipoCliente;
import org.rodrigo.cursomc.dto.ClienteNewDTO;
import org.rodrigo.cursomc.repositories.ClienteRepository;
import org.rodrigo.cursomc.resources.exception.FieldMessage;
import org.rodrigo.cursomc.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository repo;

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidCPF(objDto.getCpf_cnpj())) {
			list.add(new FieldMessage("cpf_cnpj", "CPF inválido"));
		}

		if (objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpf_cnpj())) {
			list.add(new FieldMessage("cpf_cnpj", "CNPJ inválido"));
		}

		Cliente aux = repo.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
