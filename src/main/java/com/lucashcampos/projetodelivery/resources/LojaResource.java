package com.lucashcampos.projetodelivery.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.lucashcampos.projetodelivery.domain.Loja;
import com.lucashcampos.projetodelivery.dto.LojaDTO;
import com.lucashcampos.projetodelivery.dto.NewLojaDTO;
import com.lucashcampos.projetodelivery.services.LojaService;

@RestController
@RequestMapping(value = "/lojas")
public class LojaResource {
	@Autowired
	private LojaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<LojaDTO> find(@PathVariable Integer id) {
	    Loja obj = service.find(id);
	    LojaDTO objDTO = LojaDTO.fromLoja(obj);
	    return ResponseEntity.ok().body(objDTO);
	}
	
	@RequestMapping(value= "/usuario/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Loja>> buscarLojasPorUsuario(@PathVariable Integer id) {
        List<Loja> lojas = service.findByUserId(id);
        return ResponseEntity.ok().body(lojas);
    }

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<LojaDTO>> findAll() {
	    List<Loja> list = service.findAll();
	    List<LojaDTO> listDTO = list.stream().map(LojaDTO::fromLoja).collect(Collectors.toList());
	    return ResponseEntity.ok().body(listDTO);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody NewLojaDTO objDTO) {
		objDTO.setMediaSatisfacao(0.0);
		Loja obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN','PARCEIRO')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody NewLojaDTO objDTO, @PathVariable Integer id) {
		Loja obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<NewLojaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Loja> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<NewLojaDTO> listDTO = list.map(obj -> new NewLojaDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

}
