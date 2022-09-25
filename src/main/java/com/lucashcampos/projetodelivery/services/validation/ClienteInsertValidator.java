package com.lucashcampos.projetodelivery.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucashcampos.projetodelivery.domain.Cliente;
import com.lucashcampos.projetodelivery.domain.enums.TipoCliente;
import com.lucashcampos.projetodelivery.dto.ClienteNewDTO;
import com.lucashcampos.projetodelivery.repositories.ClienteRepository;
import com.lucashcampos.projetodelivery.resources.exception.FieldMessage;
import com.lucashcampos.projetodelivery.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDTO.getCpf_cnpj())) {
			list.add(new FieldMessage("cpf_cnpj", "CPF inválido!"));
		}

		if (objDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDTO.getCpf_cnpj())) {
			list.add(new FieldMessage("cpf_cnpj", "CNPJ inválido!"));
		}

		Cliente aux = clienteRepository.findByEmail(objDTO.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Este email ja foi cadastrado."));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
