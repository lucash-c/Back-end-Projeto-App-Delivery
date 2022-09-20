package com.lucashcampos.projetodelivery.repositories.pizzas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucashcampos.projetodelivery.domain.pizza.PizzaTamanho;

@Repository
public interface PizzaTamanhoRepository extends JpaRepository<PizzaTamanho, Integer> {

}
