package br.com.senai.brabankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.brabankapi.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	
	
}
