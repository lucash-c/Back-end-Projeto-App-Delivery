package com.lucashcampos.projetodelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucashcampos.projetodelivery.domain.Adicional;

@Repository
public interface AdicionalRepository extends JpaRepository<Adicional, Integer> {

}
