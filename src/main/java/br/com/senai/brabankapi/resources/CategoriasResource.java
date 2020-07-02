package br.com.senai.brabankapi.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.senai.brabankapi.domain.Categoria;
import br.com.senai.brabankapi.services.CategoriasService;

@RestController
@RequestMapping("/categorias")
public class CategoriasResource {
	
	@Autowired
	CategoriasService categoriasService;

	@GetMapping
	public List<Categoria> listar() {
		return categoriasService.listarTodos();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> buscarPorId(@PathVariable("id") Long id) {
		Categoria categoria= categoriasService.buscarPorId(id);
		return ResponseEntity.ok(categoria);
	}
	
	@PostMapping
	public ResponseEntity<Void> inserir(@RequestBody Categoria categoria) {
		categoria = categoriasService.inserir(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(categoria.id).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable("id") Long id, @RequestBody Categoria categoria) {
		categoria.id = id;
		categoriasService.editar(categoria);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		categoriasService.deletar(id);
		return ResponseEntity.noContent().build();	
	}
}
