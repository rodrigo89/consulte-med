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

import br.com.model.Paciente;
import br.com.service.PacienteService;



@RestController
@RequestMapping("/api")
public class PacienteResources {
	@Autowired
	private PacienteService service;
	
	@PostMapping("/pacientes")
	public ResponseEntity<List<Paciente>> add(@Valid @RequestBody Paciente paciente) {
		this.service.save(paciente);
		return ResponseEntity.ok(paciente).status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping("/pacientes/{id}")
	public ResponseEntity<Paciente> remove(@PathVariable Long id) {
		Paciente paciente = this.service.getById(id);
		this.service.remove(paciente.getId());
		return ResponseEntity.ok().build();
	}
	

	
	@GetMapping("/pacientes")
	public ResponseEntity<List<Paciente>> list(){
		List<Paciente> pacientes = this.service.list();
		return !pacientes.isEmpty() ? ResponseEntity.ok(pacientes) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/pacientes/{id}")
	public ResponseEntity<Paciente> get(@PathVariable Long id){
		Paciente c = this.service.getById(id);
		return ResponseEntity.ok().body(c);
	}
	
	@PutMapping("/pacientes")
	public ResponseEntity<Paciente> edit(@Valid @RequestBody Paciente paciente){
		Paciente c = this.service.getById(paciente.getId());
		c.setNome(paciente.getNome());
		c.setEmail(paciente.getEmail());
		c.setAtivo(paciente.isAtivo());
		this.service.editar(c);
		return ResponseEntity.ok().body(c);
	}
	
	

}
