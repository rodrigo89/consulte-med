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

import br.com.model.Medico;
import br.com.service.MedicoService;



@RestController
@RequestMapping("/api")
public class MedicoResources {
	@Autowired
	private MedicoService service;
	
	@PostMapping("/medicos")
	public ResponseEntity<List<Medico>> add(@Valid @RequestBody Medico medico) {
		this.service.save(medico);
		return ResponseEntity.ok(medico).status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping("/medicos/{id}")
	public ResponseEntity<Medico> remove(@PathVariable Long id) {
		Medico medico = this.service.getById(id);
		this.service.remove(medico.getId());
		return ResponseEntity.ok().build();
	}
	

	
	@GetMapping("/medicos")
	public ResponseEntity<List<Medico>> list(){
		List<Medico> medicos = this.service.list();
		return !medicos.isEmpty() ? ResponseEntity.ok(medicos) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/medicos/{id}")
	public ResponseEntity<Medico> get(@PathVariable Long id){
		Medico c = this.service.getById(id);
		return ResponseEntity.ok().body(c);
	}
	
	@PutMapping("/medicos")
	public ResponseEntity<Medico> edit(@Valid @RequestBody Medico medico){
		Medico c = this.service.getById(medico.getId());
		c.setNome(medico.getNome());
		c.setEmail(medico.getEmail());
		c.setAtivo(medico.isAtivo());
		this.service.editar(c);
		return ResponseEntity.ok().body(c);
	}
	
	

}
