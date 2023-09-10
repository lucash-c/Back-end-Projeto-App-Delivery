package com.lucashcampos.projetodelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucashcampos.projetodelivery.domain.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {

}
