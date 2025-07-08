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
import com.lucashcampos.projetodelivery.domain.Loja;
import com.lucashcampos.projetodelivery.domain.Pizza;
import com.lucashcampos.projetodelivery.domain.PizzaSaborTamanho;
import com.lucashcampos.projetodelivery.domain.Produto;
import com.lucashcampos.projetodelivery.domain.Sorvete;
import com.lucashcampos.projetodelivery.domain.SorveteCobertura;
import com.lucashcampos.projetodelivery.domain.SorveteSabor;
import com.lucashcampos.projetodelivery.domain.enums.TipoProduto;
import com.lucashcampos.projetodelivery.dto.NewPizzaDTO;
import com.lucashcampos.projetodelivery.dto.NewProdutoDTO;
import com.lucashcampos.projetodelivery.dto.NewSorveteDTO;
import com.lucashcampos.projetodelivery.repositories.AdicionalRepository;
import com.lucashcampos.projetodelivery.repositories.CategoriaRepository;
import com.lucashcampos.projetodelivery.repositories.LojaRepository;
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

	@Autowired
	private LojaRepository lojaRepository;

	public Produto find(Integer id) {

		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + id + ", Tipo: " + Produto.class.getName()));
	}
	public Page<Produto> search(String nome, Integer categoriaId, Integer page, Integer linesPerPage, String orderBy, String direction) {
	    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
	    return repo.search(nome, categoriaId, pageRequest);
	}

	public Page<Produto> searchByLojaId(Integer lojaId, String search, Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		if (search != null && !search.isEmpty()) {
			// Se o nome for fornecido, ignora a paginação e retorna todos os produtos
			// correspondentes
			return repo.findByLojaIdAndNomeOrCodBarrasContainsIgnoreCase(lojaId, search,
					PageRequest.of(0, Integer.MAX_VALUE, Direction.valueOf(direction), orderBy));
		} else {
			// Caso contrário, usa a paginação normal
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return repo.findByLojaId(lojaId, pageRequest);
		}
	}
	
	public Page<Produto> searchByCategoryId(Integer categoryId, Integer page, Integer linesPerPage,
			String orderBy, String direction) {		
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return repo.findByCategoryId(categoryId, pageRequest);		
	}


	public Produto insert(NewProdutoDTO objDTO) {

		objDTO.setCategoria(categoriaRepository.findById(objDTO.getCategoria().getId()).get());
		Produto prod = objDTO.toProduto();
		prod.setLoja(objDTO.getLoja());
		categoriaService.update(prod.getCategoria());
		return repo.save(prod);
	}

	public Produto update(NewProdutoDTO objDTO) {

		Produto prod = objDTO.toProduto();
		prod.setId(objDTO.getId());
		prod.setDescricao(objDTO.getDescricao());
		prod.setImagem(objDTO.getImagem());
		prod.setCategoria(objDTO.getCategoria());
		categoriaService.update(prod.getCategoria());
		return repo.save(prod);
	}

	public Produto updateProduto(NewProdutoDTO objDTO) {

		// Busca o produto existente. Lança uma exceção se o produto não for encontrado.
		Produto prod = find(objDTO.getId());
		if (prod == null) {
			throw new IllegalArgumentException("Produto com o ID especificado não encontrado.");
		}

		if (objDTO.getDescricao() != null) {
			prod.setDescricao(objDTO.getDescricao());
		}

		if (objDTO.getCodBarras() != null) {
			prod.setCodBarras(objDTO.getCodBarras());
		}

		if (objDTO.getImagem() != null) {
			prod.setImagem(objDTO.getImagem());
		}

		if (objDTO.getCategoria() != null) {
			prod.setCategoria(objDTO.getCategoria());			
		}

		if (objDTO.getNome() != null) {
			prod.setNome(objDTO.getNome());
		}

		if (objDTO.getPreco() != null) {
			prod.setPreco(objDTO.getPreco());
		}

		if (objDTO.getIsActive() != null) {
			prod.setIsActive(objDTO.getIsActive());
		}

		if (objDTO.getIsVisible() != null) {
			prod.setIsVisible(objDTO.getIsVisible());
		}

		// Salva e retorna o produto atualizado no repositório.
		return repo.save(prod);
	}

	public Pizza insertPizza(NewPizzaDTO objDTO) {

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
		objDTO.setCategoria(categoriaRepository.findById(objDTO.getCategoria().getId()).get());

		objDTO.setMassa(pizzaMassaRepository.findById(objDTO.getMassa().getId()).get());

		Loja loja = lojaRepository.findById(objDTO.getLoja().getId())
				.orElseThrow(() -> new RuntimeException("Loja não encontrada"));

		objDTO.setLoja(loja);
		Pizza pizza = new Pizza(objDTO.getSaboresPizza(), objDTO.getMassa(), objDTO.getAdicionais(),
				objDTO.getObservacao(), objDTO.getMaxSabores(), objDTO.getTamanho(), objDTO.getCobrarMediaSabores());
		pizza.setCategoria(objDTO.getCategoria());
		pizza.setLoja(objDTO.getLoja());

		Double preco = 0.0;

		for (PizzaSaborTamanho item : pizza.getSabores()) {
			preco += item.getPreco();
		}

		preco = preco / pizza.getSabores().size();

		preco += pizza.getMassa().getPreco();

		for (Adicional item : pizza.getAdicionais()) {
			preco += item.getPreco();
		}
		pizza.setNome(objDTO.getNome());
		pizza.setPreco(preco);
		pizza.setTipo(2);
		pizza.setLoja(objDTO.getLoja());
		pizza.setImagem(objDTO.getImagem());
		pizza.setPessoas(objDTO.getPessoas());
		pizza.setPedacos(objDTO.getPedacos());
		
		categoriaService.update(pizza.getCategoria());
		pizzaSaborTamanhoRepository.saveAll(pizza.getSabores());
		pizzaMassaRepository.save(pizza.getMassa());
		adicionalRepository.saveAll(pizza.getAdicionais());

		return pizzaRepository.save(pizza);

	}

	public Sorvete insertSorvete(NewSorveteDTO objDTO) {

		objDTO.setLoja(lojaRepository.findById(objDTO.getLoja().getId()).get());
		objDTO.setCategoria(categoriaRepository.findById(objDTO.getCategoria().getId()).get());

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
		sorvete.setCategoria(objDTO.getCategoria());

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
		sorvete.setLoja(objDTO.getLoja());
		sorvete.setImagem(objDTO.getImagem());
		
		categoriaService.update(sorvete.getCategoria());
		categoriaService.update(sorvete.getCategoria());
		sorveteCoberturaRepository.saveAll(sorvete.getCoberturas());
		sorveteSaborRepository.saveAll(sorvete.getSabores());
		adicionalRepository.saveAll(sorvete.getAdicionais());

		return sorveteRepository.save(sorvete);

	}

	public Pizza pizzaUpdate(NewPizzaDTO objDTO) {

		objDTO.setCategoria(categoriaRepository.findById(objDTO.getCategoria().getId()).get());

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

		Loja loja = lojaRepository.findById(objDTO.getLoja().getId())
				.orElseThrow(() -> new RuntimeException("Loja não encontrada"));

		objDTO.setLoja(loja);
		Pizza pizza = new Pizza(objDTO.getSaboresPizza(), objDTO.getMassa(), objDTO.getAdicionais(),
				objDTO.getObservacao(), objDTO.getMaxSabores(), objDTO.getTamanho(), objDTO.getCobrarMediaSabores());
		pizza.setCategoria(objDTO.getCategoria());
		pizza.setLoja(objDTO.getLoja());

		Double preco = 0.0;

		for (PizzaSaborTamanho item : pizza.getSabores()) {
			preco += item.getPreco();
		}

		preco = preco / pizza.getSabores().size();

		preco += pizza.getMassa().getPreco();

		for (Adicional item : pizza.getAdicionais()) {
			preco += item.getPreco();
		}
		pizza.setNome(objDTO.getNome());
		pizza.setPreco(preco);
		pizza.setLoja(objDTO.getLoja());
		pizza.setImagem(objDTO.getImagem());
		pizza.setPessoas(objDTO.getPessoas());
		pizza.setPedacos(objDTO.getPedacos());
		pizza.setCategoria(objDTO.getCategoria());
		
		categoriaService.update(pizza.getCategoria());
		pizzaSaborTamanhoRepository.saveAll(pizza.getSabores());
		pizzaMassaRepository.save(pizza.getMassa());
		adicionalRepository.saveAll(pizza.getAdicionais());
		return pizzaRepository.save(pizza);
	}

}
