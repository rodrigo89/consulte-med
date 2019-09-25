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

import br.com.model.Agendamento;
import br.com.service.AgendamentoService;



@RestController
@RequestMapping("/api")
public class AgendamentoResources {
	@Autowired
	private AgendamentoService service;
	
	@PostMapping("/agendamentos")
	public ResponseEntity<List<Agendamento>> add(@Valid @RequestBody Agendamento agendamento) {
		this.service.save(agendamento);
		return ResponseEntity.ok(agendamento).status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping("/agendamentos/{id}")
	public ResponseEntity<Agendamento> remove(@PathVariable Long id) {
		Agendamento agendamento = this.service.getById(id);
		this.service.remove(agendamento.getId());
		return ResponseEntity.ok().build();
	}
	

	
	@GetMapping("/agendamentos")
	public ResponseEntity<List<Agendamento>> list(){
		List<Agendamento> agendamentos = this.service.list();
		return !agendamentos.isEmpty() ? ResponseEntity.ok(agendamentos) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/agendamentos/{id}")
	public ResponseEntity<Agendamento> get(@PathVariable Long id){
		Agendamento c = this.service.getById(id);
		return ResponseEntity.ok().body(c);
	}
	
	@PutMapping("/agendamentos")
	public ResponseEntity<Agendamento> edit(@Valid @RequestBody Agendamento agendamento){
		Agendamento c = this.service.getById(agendamento.getId());
		c.setPaciente(agendamento.getPaciente());
		c.setMedico(agendamento.getMedico());
		this.service.editar(c);
		return ResponseEntity.ok().body(c);
	}
	
	

}
