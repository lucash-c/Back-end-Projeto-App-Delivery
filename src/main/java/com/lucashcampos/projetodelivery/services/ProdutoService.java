package com.lucashcampos.projetodelivery.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucashcampos.projetodelivery.domain.Adicional;
import com.lucashcampos.projetodelivery.domain.Categoria;
import com.lucashcampos.projetodelivery.domain.Pizza;
import com.lucashcampos.projetodelivery.domain.PizzaSaborTamanho;
import com.lucashcampos.projetodelivery.domain.Produto;
import com.lucashcampos.projetodelivery.domain.Sorvete;
import com.lucashcampos.projetodelivery.domain.SorveteCobertura;
import com.lucashcampos.projetodelivery.domain.SorveteSabor;
import com.lucashcampos.projetodelivery.domain.enums.TipoProduto;
import com.lucashcampos.projetodelivery.dto.NewProdutoDTO;
import com.lucashcampos.projetodelivery.repositories.AdicionalRepository;
import com.lucashcampos.projetodelivery.repositories.CategoriaRepository;
import com.lucashcampos.projetodelivery.repositories.PizzaMassaRepository;
import com.lucashcampos.projetodelivery.repositories.PizzaRepository;
import com.lucashcampos.projetodelivery.repositories.PizzaSaborTamanhoRepository;
import com.lucashcampos.projetodelivery.repositories.ProdutoRepository;
import com.lucashcampos.projetodelivery.repositories.SorveteCoberturaRepository;
import com.lucashcampos.projetodelivery.repositories.SorveteRepository;
import com.lucashcampos.projetodelivery.repositories.SorveteSaborRepository;
import com.lucashcampos.projetodelivery.repositories.SorveteTamanhoRepository;
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
	private AdicionalRepository adicionalRepository;

	@Autowired
	private SorveteCoberturaRepository sorveteCoberturaRepository;

	@Autowired
	private SorveteSaborRepository sorveteSaborRepository;

	@Autowired
	private SorveteTamanhoRepository sorveteTamanhoRepository;

	@Autowired
	private SorveteRepository sorveteRepository;

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
		for (Categoria cat : objDTO.getCategorias()) {
			Categoria categoria = categoriaRepository.findById(cat.getId()).get();
			list.add(categoria);
		}
		objDTO.setCategorias(list);

		if (objDTO.getTipo().getCod() == 1) { // se for sorvete

			Sorvete sorvete = mountSorvete(objDTO);
			return sorveteRepository.save(sorvete);
		}

		if (objDTO.getTipo().getCod() == 0) { // se for pizza

			Pizza pizza = mountPizza(objDTO);
			return pizzaRepository.save(pizza);
		}

		Produto prod = objDTO.toProduto();
		prod.setRestaurante(objDTO.getRestaurante());
		for (Categoria cat : objDTO.getCategorias()) {
			cat.getProdutos().add(prod);
			categoriaService.update(cat);
		}
		return repo.save(prod);

	}

	public Produto update(NewProdutoDTO objDTO) {

		List<Categoria> list = new ArrayList<>();
		for (Categoria cat : objDTO.getCategorias()) {
			Categoria categoria = categoriaRepository.findById(cat.getId()).get();
			list.add(categoria);
		}
		objDTO.setCategorias(list);

		if (objDTO.getTipo().getCod() == 2) { // se for sorvete

			Sorvete sorvete = mountSorvete(objDTO);
			sorvete.setId(objDTO.getId());
		
			return sorveteRepository.save(sorvete);
		}

		if (objDTO.getTipo().getCod() == 1) { // se for pizza

			Pizza pizza = mountPizza(objDTO);
			pizza.setId(objDTO.getId());
		
			return pizzaRepository.save(pizza);
		}

		Produto prod = objDTO.toProduto();
		prod.setCategorias(objDTO.getCategorias());
		for (Categoria cat : prod.getCategorias()) {
			cat.getProdutos().add(prod);
			categoriaService.update(cat);
		}
		prod.setId(objDTO.getId());
		prod.setDescricao(objDTO.getDescricao());
		prod.setImagem(objDTO.getImagem());
		return repo.save(prod);

	}

	private Pizza mountPizza(NewProdutoDTO objDTO) {

		List<PizzaSaborTamanho> listSabores = new ArrayList<>();
		for (PizzaSaborTamanho item : objDTO.getSaboresPizza()) {
			listSabores.add(pizzaSaborTamanhoRepository.findById(item.getId()).get());
		}
		objDTO.setSaboresPizza(listSabores);

		List<Adicional> listAdicionais = new ArrayList<>();
		for (Adicional item : objDTO.getAdicionais()) {
			listAdicionais.add(adicionalRepository.findById(item.getId()).get());
		}
		objDTO.setAdicionais(listAdicionais);

		objDTO.setMassa(pizzaMassaRepository.findById(objDTO.getMassa().getId()).get());

		Pizza pizza = new Pizza(objDTO.getSaboresPizza(), objDTO.getMassa(), objDTO.getAdicionais(),
				objDTO.getObservacao());
		pizza.setCategorias(objDTO.getCategorias());
		for (Categoria cat : pizza.getCategorias()) {
			cat.getProdutos().add(pizza);
			categoriaService.update(cat);
		}

		Double preco = 0.0;

		for (PizzaSaborTamanho item : pizza.getSabores()) {
			preco += item.getPreco();
		}

		preco = preco / pizza.getSabores().size();

		preco += pizza.getMassa().getPreco();

		for (Adicional item : pizza.getAdicionais()) {
			preco += item.getPreco();
		}

		pizza.setPreco(preco);
		pizza.setRestaurante(objDTO.getRestaurante());

		pizzaSaborTamanhoRepository.saveAll(pizza.getSabores());
		pizzaMassaRepository.save(pizza.getMassa());
		adicionalRepository.saveAll(pizza.getAdicionais());

		return pizza;
	}

	private Sorvete mountSorvete(NewProdutoDTO objDTO) {

		List<SorveteCobertura> listCoberturas = new ArrayList<>();
		for (SorveteCobertura item : objDTO.getCoberturas()) {
			listCoberturas.add(sorveteCoberturaRepository.findById(item.getId()).get());
		}
		objDTO.setCoberturas(listCoberturas);

		List<Adicional> listAdicionais = new ArrayList<>();
		for (Adicional item : objDTO.getAdicionais()) {
			listAdicionais.add(adicionalRepository.findById(item.getId()).get());
		}
		objDTO.setAdicionais(listAdicionais);

		List<SorveteSabor> listSabores = new ArrayList<>();
		for (SorveteSabor item : objDTO.getSaboresSorvete()) {
			listSabores.add(sorveteSaborRepository.findById(item.getId()).get());
		}

		objDTO.setTamanhoSorvete(sorveteTamanhoRepository.findById(objDTO.getTamanhoSorvete().getId()).get());

		Sorvete sorvete = new Sorvete(objDTO.getTamanhoSorvete(), objDTO.getSaboresSorvete(), objDTO.getAdicionais(),
				objDTO.getCoberturas(), TipoProduto.SORVETE.getCod());
		sorvete.setCategorias(objDTO.getCategorias());
		for (Categoria cat : sorvete.getCategorias()) {
			cat.getProdutos().add(sorvete);
			categoriaService.update(cat);
		}

		Double preco = 0.0;
		preco += sorvete.getTamanho().getPreco();

		for (Adicional item : sorvete.getAdicionais()) {
			preco += item.getPreco();
		}

		for (SorveteSabor item : sorvete.getSabores()) {
			preco += item.getValorAdicional();
		}

		for (SorveteCobertura item : sorvete.getCoberturas()) {
			preco += item.getValorAdicional();
		}

		sorvete.setPreco(preco);
		sorvete.setRestaurante(objDTO.getRestaurante());

		sorveteCoberturaRepository.saveAll(sorvete.getCoberturas());
		sorveteSaborRepository.saveAll(sorvete.getSabores());
		adicionalRepository.saveAll(sorvete.getAdicionais());

		return sorvete;
	}

}
