package br.com.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.Contato;
import br.com.service.ContatoService;



@RestController
@RequestMapping("/api")
public class ContatoResources {
	@Autowired
	private ContatoService service;
	
	@PostMapping("/contatos")
	public ResponseEntity<List<Contato>> add(@Valid @RequestBody Contato contato) {
		this.service.save(contato);
		return ResponseEntity.ok(contato).status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping("/contatos/{id}")
	public ResponseEntity<Contato> remove(@PathVariable Long id) {
		Contato contato = this.service.getById(id);
		this.service.remove(contato.getId());
		return ResponseEntity.ok().build();
	}
	

	
	@GetMapping("/contatos")
	public ResponseEntity<List<Contato>> list(){
		List<Contato> contatos = this.service.list();
		return !contatos.isEmpty() ? ResponseEntity.ok(contatos) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/contatos/{id}")
	public ResponseEntity<Contato> get(@PathVariable Long id){
		Contato c = this.service.getById(id);
		return ResponseEntity.ok().body(c);
	}
	
	@PutMapping("/contatos")
	public ResponseEntity<Contato> edit(@Valid @RequestBody Contato contato){
		Contato c = this.service.getById(contato.getId());
		c.setNome(contato.getNome());
		c.setEmail(contato.getEmail());
		c.setAtivo(contato.isAtivo());
		this.service.editar(c);
		return ResponseEntity.ok().body(c);
	}
	
	

}
