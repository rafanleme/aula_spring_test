package br.com.senai.brabankapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.senai.brabankapi.domain.Categoria;
import br.com.senai.brabankapi.repository.CategoriaRepository;
import br.com.senai.brabankapi.services.exceptions.CategoriaNaoEncontradaException;

@Service
public class CategoriasService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	public List<Categoria> listarTodos(){
		return categoriaRepository.findAll();
	}
	
	public Categoria buscarPorId(Long id) {
		Categoria categoria = categoriaRepository.findById(id).orElse(null);
		if(categoria == null) {
			throw new CategoriaNaoEncontradaException
				("A categoria não pode ser localizada!");
		}
		return categoria;
	}
	
	public Categoria inserir(Categoria categoria) {
		categoria.id = null;
		return categoriaRepository.save(categoria);
	}
	
	public void editar(Categoria categoria) {
		verificarExistencia(categoria);
		categoriaRepository.save(categoria);
	}
	
	private void verificarExistencia(Categoria categoria) {
		buscarPorId(categoria.id);
	}
	
	public void deletar(Long id) {
		try {	
			categoriaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {	
			throw new CategoriaNaoEncontradaException
				("A categoria não pode ser localizada!");
		}
	}
	

}
