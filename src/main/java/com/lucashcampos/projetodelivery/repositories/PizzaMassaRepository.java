package com.lucashcampos.projetodelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucashcampos.projetodelivery.domain.PizzaMassa;

@Repository
public interface PizzaMassaRepository extends JpaRepository<PizzaMassa, Integer> {

}
