package com.lucashcampos.projetodelivery.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucashcampos.projetodelivery.domain.Pedido;
import com.lucashcampos.projetodelivery.repositories.PedidoRepository;
import com.lucashcampos.projetodelivery.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id) {

		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
