package com.lucashcampos.projetodelivery.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucashcampos.projetodelivery.domain.PizzaSaborTamanho;
import com.lucashcampos.projetodelivery.repositories.PizzaSaborTamanhoRepository;
import com.lucashcampos.projetodelivery.services.exceptions.DataIntegrityException;
import com.lucashcampos.projetodelivery.services.exceptions.ObjectNotFoundException;

@Service
public class PizzaSaborTamanhoService {

	@Autowired
	private PizzaSaborTamanhoRepository repo;

	public PizzaSaborTamanho find(Integer id) {
		Optional<PizzaSaborTamanho> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + id + ", Tipo: " + PizzaSaborTamanho.class.getName()));
	}

	public List<PizzaSaborTamanho> findAll() {
		return repo.findAll();
	}

	public List<PizzaSaborTamanho> insertAll(List<PizzaSaborTamanho> objs) {
	    return repo.saveAll(objs);
	}

	public PizzaSaborTamanho update(PizzaSaborTamanho obj) {
		PizzaSaborTamanho newObj = find(obj.getId());
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

	public Page<PizzaSaborTamanho> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	private void updateData(PizzaSaborTamanho newObj, PizzaSaborTamanho obj) {
		newObj.setDescricao(obj.getDescricao());		
		newObj.setSabor(obj.getSabor());
		newObj.setPreco(obj.getPreco());
		newObj.setTamanho(obj.getTamanho());
	}

}
