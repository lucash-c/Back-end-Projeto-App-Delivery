package com.lucashcampos.projetodelivery.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucashcampos.projetodelivery.domain.Pizza;
import com.lucashcampos.projetodelivery.domain.Produto;
import com.lucashcampos.projetodelivery.domain.Sorvete;
import com.lucashcampos.projetodelivery.dto.NewPizzaDTO;
import com.lucashcampos.projetodelivery.dto.NewProdutoDTO;
import com.lucashcampos.projetodelivery.dto.NewSorveteDTO;
import com.lucashcampos.projetodelivery.dto.ProdutoDTO;
import com.lucashcampos.projetodelivery.resources.utils.URL;
import com.lucashcampos.projetodelivery.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	@Autowired
	private ProdutoService service;	

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
	        @RequestParam(value = "nome", defaultValue = "") String nome,
	        @RequestParam(value = "categoria", defaultValue = "") Integer categoriaId, // Apenas uma categoria
	        @RequestParam(value = "page", defaultValue = "0") Integer page,
	        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
	        @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
	        @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

	    String nomeDecoded = URL.decodeParam(nome);	    
	    
	    Page<Produto> list = service.search(nomeDecoded, categoriaId, page, linesPerPage, orderBy, direction);
	    Page<ProdutoDTO> listDTO = list.map(ProdutoDTO::new);

	    return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value = "/loja", method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPageByLojaId(
			@RequestParam(value = "lojaId", defaultValue = "") Integer lojaId,
			@RequestParam(value = "search", defaultValue = "") String search,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {		
		
		Page<Produto> list = service.searchByLojaId(lojaId, search, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value = "/categoria", method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPageByCategoryId(
			@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {		
		
		Page<Produto> list = service.searchByCategoryId(categoryId, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody NewProdutoDTO objDTO) {

		Produto obj = service.insert(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody NewProdutoDTO objDTO, @PathVariable Integer id) {
		objDTO.setId(id);
		service.updateProduto(objDTO);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/pizza", method = RequestMethod.POST)
	public ResponseEntity<Void> insertPizza(@RequestBody NewPizzaDTO objDTO) {

		Pizza obj = service.insertPizza(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/sorvete", method = RequestMethod.POST)
	public ResponseEntity<Void> insertSorvete(@RequestBody NewSorveteDTO objDTO) {

		Sorvete obj = service.insertSorvete(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
