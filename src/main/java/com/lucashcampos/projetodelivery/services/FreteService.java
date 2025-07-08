package com.lucashcampos.projetodelivery.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucashcampos.projetodelivery.domain.Frete;
import com.lucashcampos.projetodelivery.repositories.FreteRepository;
import com.lucashcampos.projetodelivery.services.exceptions.DataIntegrityException;
import com.lucashcampos.projetodelivery.services.exceptions.ObjectNotFoundException;

@Service
public class FreteService {

	@Autowired
	private FreteRepository repo;

	public Frete find(Integer id) {
		Optional<Frete> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + id + ", Tipo: " + Frete.class.getName()));
	}

	public List<Frete> findAll() {
		return repo.findAll();
	}

	public Frete insert(Frete obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Frete update(Frete obj) {
		Frete newObj = find(obj.getId());
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

	public Page<Frete> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	private void updateData(Frete newObj, Frete obj) {
		newObj.setKm(obj.getKm());
		newObj.setValor(obj.getValor());
		newObj.setPrazo(obj.getPrazo());
	}

}
