package com.lucashcampos.projetodelivery.repositories.pizzas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucashcampos.projetodelivery.domain.pizza.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

}
