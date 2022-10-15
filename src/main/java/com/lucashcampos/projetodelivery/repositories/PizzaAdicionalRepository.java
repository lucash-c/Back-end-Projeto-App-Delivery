package com.lucashcampos.projetodelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucashcampos.projetodelivery.domain.PizzaAdicional;

@Repository
public interface PizzaAdicionalRepository extends JpaRepository<PizzaAdicional, Integer> {

}
