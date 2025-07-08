package com.lucashcampos.projetodelivery.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucashcampos.projetodelivery.domain.Loja;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Integer> {
	
	@Query("SELECT l FROM Loja l JOIN l.usuarios u WHERE u.id = :usuarioId")
    List<Loja> findByUsuarioId(@Param("usuarioId") Integer usuarioId);

}
