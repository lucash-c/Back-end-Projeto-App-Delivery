package com.lucashcampos.projetodelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucashcampos.projetodelivery.domain.PizzaSaborTamanho;

@Repository
public interface PizzaSaborTamanhoRepository extends JpaRepository<PizzaSaborTamanho, Integer> {

}
