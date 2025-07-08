package com.lucashcampos.projetodelivery.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucashcampos.projetodelivery.domain.Categoria;
import com.lucashcampos.projetodelivery.domain.Loja;
import com.lucashcampos.projetodelivery.domain.Produto;
import com.lucashcampos.projetodelivery.dto.CategoriaDTO;
import com.lucashcampos.projetodelivery.dto.CategoriaProdutoDTO;
import com.lucashcampos.projetodelivery.dto.ProdutoDTO;
import com.lucashcampos.projetodelivery.repositories.CategoriaRepository;
import com.lucashcampos.projetodelivery.repositories.LojaRepository;
import com.lucashcampos.projetodelivery.repositories.ProdutoRepository;
import com.lucashcampos.projetodelivery.services.exceptions.DataIntegrityException;
import com.lucashcampos.projetodelivery.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private LojaRepository lojaRepository;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos!");
		}

	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Categoria fromDTO(CategoriaDTO objDTO) {	
		Loja loja = lojaRepository.findById(objDTO.getLojaId()).get();
		return new Categoria(objDTO.getId(), objDTO.getNome(), loja, objDTO.getIsActive());
	}

	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());		
		newObj.setLoja(lojaRepository.findById(obj.getLoja().getId()).get());
		newObj.setIsActive(obj.getIsActive());
	}

	public List<CategoriaProdutoDTO> findAllByLojaId(Integer lojaId) {
		List<Categoria> categorias = repo.findByLojaId(lojaId).orElse(new ArrayList<>());

		if (categorias.isEmpty()) {
			throw new ObjectNotFoundException("Não foi encontrado nenhuma categoria para esta loja! Id " + lojaId
					+ ", Tipo: " + Categoria.class.getName());
		}

		List<CategoriaProdutoDTO> categoriaProdutoDTOs = new ArrayList<>();
		for (Categoria categoria : categorias) {
			List<Produto> produtos = produtoRepository.findByCategorias_Id(categoria.getId());

			List<ProdutoDTO> produtoDTOs = produtos.stream().map(produto -> new ProdutoDTO(produto))
					.collect(Collectors.toList());

			categoriaProdutoDTOs.add(new CategoriaProdutoDTO(categoria.getId(), categoria.getNome(), produtoDTOs));
		}

		return categoriaProdutoDTOs;
	}

}
