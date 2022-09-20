package com.lucashcampos.projetodelivery.repositories.pizzas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucashcampos.projetodelivery.domain.pizza.PizzaMassa;

@Repository
public interface PizzaMassaRepository extends JpaRepository<PizzaMassa, Integer> {

}
