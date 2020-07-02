package br.com.senai.brabankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.brabankapi.domain.Usuario;

/*
 	Mapeia a tabela usuário para manipular os registros;
 	passamos dois parametros
  	primeiro a entidade Usuario e o tipo di atributo identificador 
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Usuario findByEmail(String email);
	
}
