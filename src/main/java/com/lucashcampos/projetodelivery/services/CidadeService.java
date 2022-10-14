package com.lucashcampos.projetodelivery.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucashcampos.projetodelivery.domain.Cidade;
import com.lucashcampos.projetodelivery.repositories.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	CidadeRepository repo;

	public List<Cidade> findByEstado(Integer estadoId) {
		return repo.findCidades(estadoId);
	}

}
