package com.lucashcampos.projetodelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucashcampos.projetodelivery.domain.Sorvete;

@Repository
public interface SorveteRepository extends JpaRepository<Sorvete, Integer> {

}
