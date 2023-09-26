package com.lucashcampos.projetodelivery.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucashcampos.projetodelivery.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	
	@Transactional(readOnly = true)
	Optional<List<Categoria>> findByLojaId(Integer lojaId);

}
