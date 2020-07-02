package br.com.senai.brabankapi.services;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.senai.brabankapi.domain.Usuario;
import br.com.senai.brabankapi.repository.UsuarioRepository;
import br.com.senai.brabankapi.services.exceptions.UsuarioNaoEncontradoException;

@Service
public class UsuariosService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public List<Usuario> listarTodos(){
		return usuarioRepository.findAll();
	}
	
	public Usuario buscarPorId(Long id) {
		Usuario usuario = usuarioRepository.findById(id).orElse(null);
		if(usuario == null) {
			throw new UsuarioNaoEncontradoException
				("O usuário não pode ser localizado!");
		}
		return usuario;
	}
	
	public Usuario inserir(Usuario usuario) {
		usuario.id = null;
		return usuarioRepository.save(usuario);
	}
	
	public void editar(Usuario usuario) {
		verificarExistencia(usuario);
		usuarioRepository.save(usuario);
	}
	
	private void verificarExistencia(Usuario usuario) {
		buscarPorId(usuario.id);
	}
	
	public void deletar(Long id) {
		try {	
			usuarioRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {	
			throw new UsuarioNaoEncontradoException
				("Usuário não pode ser localizado");
		}
	}
	
	public Usuario buscarPorEmail(String email) {
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		if(usuario == null) {
			throw new UsuarioNaoEncontradoException
				("O usuário não pode ser localizado!");
		}
		
		return usuario;
		
	}
	

}
