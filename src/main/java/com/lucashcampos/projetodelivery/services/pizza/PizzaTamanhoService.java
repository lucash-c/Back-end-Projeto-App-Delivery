package com.lucashcampos.projetodelivery.services.pizza;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucashcampos.projetodelivery.domain.pizza.PizzaTamanho;
import com.lucashcampos.projetodelivery.repositories.pizzas.PizzaTamanhoRepository;
import com.lucashcampos.projetodelivery.services.exceptions.DataIntegrityException;
import com.lucashcampos.projetodelivery.services.exceptions.ObjectNotFoundException;

@Service
public class PizzaTamanhoService {

	@Autowired
	private PizzaTamanhoRepository repo;

	public PizzaTamanho find(Integer id) {
		Optional<PizzaTamanho> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + id + ", Tipo: " + PizzaTamanho.class.getName()));
	}

	public List<PizzaTamanho> findAll() {
		return repo.findAll();
	}

	public PizzaTamanho insert(PizzaTamanho obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public PizzaTamanho update(PizzaTamanho obj) {
		PizzaTamanho newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possivel excluir este item pois existem produtos relacionados a ele!");
		}

	}

	public Page<PizzaTamanho> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	private void updateData(PizzaTamanho newObj, PizzaTamanho obj) {
		newObj.setNome(obj.getNome());
		newObj.setPedacos(obj.getPedacos());
		newObj.setPessoas(obj.getPessoas());
	}

}
