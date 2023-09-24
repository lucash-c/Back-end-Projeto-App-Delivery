package com.lucashcampos.projetodelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucashcampos.projetodelivery.domain.Loja;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Integer> {

}
