package com.lucashcampos.projetodelivery.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucashcampos.projetodelivery.domain.Categoria;
import com.lucashcampos.projetodelivery.domain.Pizza;
import com.lucashcampos.projetodelivery.domain.Adicional;
import com.lucashcampos.projetodelivery.domain.PizzaSaborTamanho;
import com.lucashcampos.projetodelivery.domain.Produto;
import com.lucashcampos.projetodelivery.dto.NewProdutoDTO;
import com.lucashcampos.projetodelivery.repositories.CategoriaRepository;
import com.lucashcampos.projetodelivery.repositories.AdicionalRepository;
import com.lucashcampos.projetodelivery.repositories.PizzaMassaRepository;
import com.lucashcampos.projetodelivery.repositories.PizzaRepository;
import com.lucashcampos.projetodelivery.repositories.PizzaSaborTamanhoRepository;
import com.lucashcampos.projetodelivery.repositories.ProdutoRepository;
import com.lucashcampos.projetodelivery.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private PizzaRepository pizzaRepository;

	@Autowired
	private PizzaSaborTamanhoRepository pizzaSaborTamanhoRepository;

	@Autowired
	private PizzaMassaRepository pizzaMassaRepository;

	@Autowired
	private AdicionalRepository pizzaAdicionalRepository;

	public Produto find(Integer id) {

		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + id + ", Tipo: " + Produto.class.getName()));
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.search(nome, categorias, pageRequest);

	}

	public Produto insert(NewProdutoDTO objDTO) {

		List<Categoria> list = new ArrayList<>();
		Produto obj = objDTO.toProduto();

		for (Categoria cat : obj.getCategorias()) {
			Categoria categoria = categoriaRepository.findById(cat.getId()).get();
			list.add(categoria);
		}
		obj.setCategorias(list);

		for (Categoria cat : obj.getCategorias()) {
			cat.getProdutos().add(obj);
			categoriaService.update(cat);
		}

		if (!objDTO.getSabores().isEmpty()) {
			List<PizzaSaborTamanho> listSabores = new ArrayList<>();
			for(PizzaSaborTamanho item : objDTO.getSabores()) {
				listSabores.add(pizzaSaborTamanhoRepository.findById(item.getId()).get());
			}
			objDTO.setSabores(listSabores);
			
			List<Adicional> listAdicionais = new ArrayList<>();
			for(Adicional item : objDTO.getAdicionais()) {
				listAdicionais.add(pizzaAdicionalRepository.findById(item.getId()).get());
			}
			objDTO.setAdicionais(listAdicionais);
			
			objDTO.setMassa(pizzaMassaRepository.findById(objDTO.getMassa().getId()).get());
			
			Pizza pizza = new Pizza(objDTO.getSabores(), objDTO.getMassa(), objDTO.getAdicionais(),
					objDTO.getObservacao());
			
			pizza.setPreco(obj.getPreco());
			pizzaSaborTamanhoRepository.saveAll(pizza.getSabores());
			pizzaMassaRepository.save(pizza.getMassa());
			pizzaAdicionalRepository.saveAll(pizza.getAdicionais());
			return pizzaRepository.save(pizza);
		}
		return repo.save(obj);
	}
}
