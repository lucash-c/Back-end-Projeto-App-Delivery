package com.lucashcampos.projetodelivery.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucashcampos.projetodelivery.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Produto obj " +
		       "WHERE UPPER(obj.nome) LIKE UPPER(concat('%', :nome, '%')) " +
		       "AND obj.categoria.id = :categoriaId " +
		       "AND obj.isVisible = true")
	Page<Produto> search(@Param("nome") String nome, @Param("categoria") Integer categoriaId,
			Pageable pageRequest);

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Produto obj " + "WHERE obj.loja.id = :lojaId "
			+ "AND (UPPER(obj.nome) LIKE UPPER(concat('%', :search, '%')) "
			+ "OR obj.codBarras LIKE concat('%', :search, '%')) " + "AND obj.isVisible = true")
	Page<Produto> findByLojaIdAndNomeOrCodBarrasContainsIgnoreCase(@Param("lojaId") Integer lojaId,
			@Param("search") String search, Pageable pageable);

	// Busca produtos apenas pelo lojaId e filtra por isVisible = true
	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Produto obj WHERE obj.loja.id = :lojaId AND obj.isVisible = true")
	Page<Produto> findByLojaId(@Param("lojaId") Integer lojaId, Pageable pageable);

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Produto obj INNER JOIN obj.categoria cat WHERE cat.id = :categoriaId AND obj.isVisible = true")
	List<Produto> findByCategorias_Id(@Param("categoriaId") Integer categoriaId);
	
	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Produto obj INNER JOIN obj.categoria cat WHERE cat.id = :categoryId AND obj.isVisible = true")
	Page<Produto> findByCategoryId(@Param("categoryId") Integer categoryId,  Pageable pageable);
}
