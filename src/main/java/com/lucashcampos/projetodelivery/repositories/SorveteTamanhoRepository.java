package com.lucashcampos.projetodelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucashcampos.projetodelivery.domain.SorveteTamanho;

@Repository
public interface SorveteTamanhoRepository extends JpaRepository<SorveteTamanho, Integer> {

}
