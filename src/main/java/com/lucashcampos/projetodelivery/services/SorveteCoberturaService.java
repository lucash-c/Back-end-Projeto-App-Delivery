package com.lucashcampos.projetodelivery.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucashcampos.projetodelivery.domain.SorveteCobertura;
import com.lucashcampos.projetodelivery.repositories.SorveteCoberturaRepository;
import com.lucashcampos.projetodelivery.services.exceptions.DataIntegrityException;
import com.lucashcampos.projetodelivery.services.exceptions.ObjectNotFoundException;

@Service
public class SorveteCoberturaService {

	@Autowired
	private SorveteCoberturaRepository repo;

	public SorveteCobertura find(Integer id) {
		Optional<SorveteCobertura> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + id + ", Tipo: " + SorveteCobertura.class.getName()));
	}

	public List<SorveteCobertura> findAll() {
		return repo.findAll();
	}

	public SorveteCobertura insert(SorveteCobertura obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public SorveteCobertura update(SorveteCobertura obj) {
		SorveteCobertura newObj = find(obj.getId());
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

	public Page<SorveteCobertura> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	private void updateData(SorveteCobertura newObj, SorveteCobertura obj) {

		if (obj.getNome() != null)
			newObj.setNome(obj.getNome());

		if (obj.getValorAdicional() != null)
			newObj.setValorAdicional(obj.getValorAdicional());

		if (obj.getRestaurante() != null)
			newObj.setRestaurante(obj.getRestaurante());
	}

}
