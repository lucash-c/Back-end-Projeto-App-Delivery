package com.lucashcampos.projetodelivery.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucashcampos.projetodelivery.domain.PizzaMassa;
import com.lucashcampos.projetodelivery.repositories.PizzaMassaRepository;
import com.lucashcampos.projetodelivery.services.exceptions.DataIntegrityException;
import com.lucashcampos.projetodelivery.services.exceptions.ObjectNotFoundException;

@Service
public class PizzaMassaService {

	@Autowired
	private PizzaMassaRepository repo;

	public PizzaMassa find(Integer id) {
		Optional<PizzaMassa> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + id + ", Tipo: " + PizzaMassa.class.getName()));
	}

	public List<PizzaMassa> findAll() {
		return repo.findAll();
	}

	public PizzaMassa insert(PizzaMassa obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public PizzaMassa update(PizzaMassa obj) {
		PizzaMassa newObj = find(obj.getId());
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

	public Page<PizzaMassa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	private void updateData(PizzaMassa newObj, PizzaMassa obj) {
		newObj.setNome(obj.getNome());
		newObj.setPreco(obj.getPreco());
	}

}
