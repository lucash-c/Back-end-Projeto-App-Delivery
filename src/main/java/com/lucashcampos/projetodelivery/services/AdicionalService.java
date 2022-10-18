package com.lucashcampos.projetodelivery.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucashcampos.projetodelivery.domain.Adicional;
import com.lucashcampos.projetodelivery.repositories.AdicionalRepository;
import com.lucashcampos.projetodelivery.services.exceptions.DataIntegrityException;
import com.lucashcampos.projetodelivery.services.exceptions.ObjectNotFoundException;

@Service
public class AdicionalService {

	@Autowired
	private AdicionalRepository repo;

	public Adicional find(Integer id) {
		Optional<Adicional> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + id + ", Tipo: " + Adicional.class.getName()));
	}

	public List<Adicional> findAll() {
		return repo.findAll();
	}

	public Adicional insert(Adicional obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Adicional update(Adicional obj) {
		Adicional newObj = find(obj.getId());
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

	public Page<Adicional> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	private void updateData(Adicional newObj, Adicional obj) {
		newObj.setNome(obj.getNome());
		newObj.setPreco(obj.getPreco());
	}

}
