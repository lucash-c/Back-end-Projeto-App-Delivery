package com.lucashcampos.projetodelivery.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucashcampos.projetodelivery.domain.Cidade;
import com.lucashcampos.projetodelivery.domain.Cliente;
import com.lucashcampos.projetodelivery.domain.Endereco;
import com.lucashcampos.projetodelivery.domain.enums.TipoCliente;
import com.lucashcampos.projetodelivery.dto.ClienteDTO;
import com.lucashcampos.projetodelivery.dto.ClienteNewDTO;
import com.lucashcampos.projetodelivery.repositories.ClienteRepository;
import com.lucashcampos.projetodelivery.repositories.EnderecoRepository;
import com.lucashcampos.projetodelivery.services.exceptions.DataIntegrityException;
import com.lucashcampos.projetodelivery.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {

		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj=repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
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
			throw new DataIntegrityException("Não é possivel excluir este cliente, pois há pedidos relacionados");
		}

	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), null, objDTO.getNome(), objDTO.getEmail(), null, null);
	}

	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getCpf_cnpj(), objDTO.getNome(), objDTO.getEmail(),
				TipoCliente.toEnum(objDTO.getTipo()),pe.encode(objDTO.getSenha()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(),
				objDTO.getBairro(), objDTO.getCep(), cid, cli);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if (objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
