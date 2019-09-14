package br.com.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public void add() {
		
	}
	
	@DeleteMapping("/pacientes/{id}")
	public void remove(@PathVariable Long id) {
		this.service.remove(id);
	}
	
	@PutMapping("/pacientes")
	public void edit() {
		
	}
	
	@GetMapping("/pacientes")
	public ResponseEntity<List<Paciente>> list(){
		List<Paciente> pacientes = this.service.lista();
		return !pacientes.isEmpty() ? ResponseEntity.ok(pacientes) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/paciente/{id}")
	public Paciente get(Long id){
		return null;
	}
	
	

}
