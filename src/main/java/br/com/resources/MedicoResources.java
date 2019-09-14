package br.com.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
		this.service.salva(medico);
		return ResponseEntity.ok(medico).status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping("/medicos/{id}")
	public void remove() {
		
	}
	
	@PutMapping("/medicos")
	public void edit() {
		
	}
	
	@GetMapping("/medicos")
	public ResponseEntity<List<Medico>> list(){
		List<Medico> medicos = this.service.lista();
		return !medicos.isEmpty() ? ResponseEntity.ok(medicos) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/medico/{id}")
	public Medico get(Long id){
		return null;
	}
	
	

}
