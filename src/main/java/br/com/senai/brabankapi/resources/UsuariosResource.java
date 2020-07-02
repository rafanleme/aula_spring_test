package br.com.senai.brabankapi.resources;

import java.net.URI;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.senai.brabankapi.domain.Usuario;
import br.com.senai.brabankapi.services.UsuariosService;
import br.com.senai.brabankapi.services.exceptions.UsuarioNaoEncontradoException;

@Controller
@RequestMapping("/usuarios")
public class UsuariosResource {
	
	@Autowired
	UsuariosService usuariosService;

	@GetMapping
	public @ResponseBody List<Usuario> listar() {
		return usuariosService.listarTodos();
	}
	
	@GetMapping(value = "/email/{email}")
	public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email){
		Usuario usuario = usuariosService.buscarPorEmail(email);
		return ResponseEntity.ok(usuario);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> buscarPorId(@PathVariable("id") Long id) {
		Usuario usuario= usuariosService.buscarPorId(id);
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping
	public ResponseEntity<Void> inserir(@RequestBody Usuario usuario) {
		try {
			usuario = usuariosService.inserir(usuario);
		} catch (DataIntegrityViolationException e) {
			ResponseEntity.badRequest().build();
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(usuario.id).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
		usuario.id = id;
		usuariosService.editar(usuario);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		usuariosService.deletar(id);
		return ResponseEntity.noContent().build();	
	}
}
