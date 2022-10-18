package com.lucashcampos.projetodelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucashcampos.projetodelivery.domain.SorveteSabor;

@Repository
public interface SorveteSaborRepository extends JpaRepository<SorveteSabor, Integer> {
	
}
