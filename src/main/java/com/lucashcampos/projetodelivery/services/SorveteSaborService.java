package com.lucashcampos.projetodelivery.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucashcampos.projetodelivery.domain.SorveteSabor;
import com.lucashcampos.projetodelivery.repositories.SorveteSaborRepository;
import com.lucashcampos.projetodelivery.services.exceptions.DataIntegrityException;
import com.lucashcampos.projetodelivery.services.exceptions.ObjectNotFoundException;

@Service
public class SorveteSaborService {

	@Autowired
	private SorveteSaborRepository repo;

	public SorveteSabor find(Integer id) {
		Optional<SorveteSabor> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + id + ", Tipo: " + SorveteSabor.class.getName()));
	}

	public List<SorveteSabor> findAll() {
		return repo.findAll();
	}

	public SorveteSabor insert(SorveteSabor obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public SorveteSabor update(SorveteSabor obj) {
		SorveteSabor newObj = find(obj.getId());
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

	public Page<SorveteSabor> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	private void updateData(SorveteSabor newObj, SorveteSabor obj) {

		if (obj.getNome() != null)
			newObj.setNome(obj.getNome());

		if (obj.getDescricao() != null)
			newObj.setDescricao(obj.getDescricao());

		if (obj.getValorAdicional() != null)
			newObj.setValorAdicional(obj.getValorAdicional());

		if (obj.getLoja() != null)
			newObj.setLoja(obj.getLoja());
	}

}
