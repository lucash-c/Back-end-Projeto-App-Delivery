package com.lucashcampos.projetodelivery.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucashcampos.projetodelivery.domain.SorveteTamanho;
import com.lucashcampos.projetodelivery.repositories.SorveteTamanhoRepository;
import com.lucashcampos.projetodelivery.services.exceptions.DataIntegrityException;
import com.lucashcampos.projetodelivery.services.exceptions.ObjectNotFoundException;

@Service
public class SorveteTamanhoService {

	@Autowired
	private SorveteTamanhoRepository repo;

	public SorveteTamanho find(Integer id) {
		Optional<SorveteTamanho> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + id + ", Tipo: " + SorveteTamanho.class.getName()));
	}

	public List<SorveteTamanho> findAll() {
		return repo.findAll();
	}

	public SorveteTamanho insert(SorveteTamanho obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public SorveteTamanho update(SorveteTamanho obj) {
		SorveteTamanho newObj = find(obj.getId());
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

	public Page<SorveteTamanho> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	private void updateData(SorveteTamanho newObj, SorveteTamanho obj) {

		if (obj.getNome() != null)
			newObj.setNome(obj.getNome());

		if (obj.getDescricao() != null)
			newObj.setDescricao(obj.getDescricao());

		if (obj.getPreco() != null)
			newObj.setPreco(obj.getPreco());

		if (obj.getQuantidadeSabores() != null)
			newObj.setQuantidadeSabores(obj.getQuantidadeSabores());

		if (obj.getLoja() != null)
			newObj.setLoja(obj.getLoja());
	}

}
