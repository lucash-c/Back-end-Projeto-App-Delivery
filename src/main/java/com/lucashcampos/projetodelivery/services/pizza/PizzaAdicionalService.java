package com.lucashcampos.projetodelivery.services.pizza;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucashcampos.projetodelivery.domain.pizza.PizzaAdicional;
import com.lucashcampos.projetodelivery.repositories.pizzas.PizzaAdicionalRepository;
import com.lucashcampos.projetodelivery.services.exceptions.DataIntegrityException;
import com.lucashcampos.projetodelivery.services.exceptions.ObjectNotFoundException;

@Service
public class PizzaAdicionalService {

	@Autowired
	private PizzaAdicionalRepository repo;

	public PizzaAdicional find(Integer id) {
		Optional<PizzaAdicional> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + id + ", Tipo: " + PizzaAdicional.class.getName()));
	}

	public List<PizzaAdicional> findAll() {
		return repo.findAll();
	}

	public PizzaAdicional insert(PizzaAdicional obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public PizzaAdicional update(PizzaAdicional obj) {
		PizzaAdicional newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir este item pois existem produtos relacionados a ele!");
		}

	}

	public Page<PizzaAdicional> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	private void updateData(PizzaAdicional newObj, PizzaAdicional obj) {
		newObj.setNome(obj.getNome());
		newObj.setPreco(obj.getPreco());
	}

}
