/**
 * 
 */
package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.model.Paciente;
import br.com.model.dto.PacientePesquisaDTO;
import br.com.repository.PacienteRepository;


/**
 * @author carlosbarbosagomesfilho
 *
 */
@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repository;
	
	@Transactional(readOnly=true)
	public List<Paciente> lista(){
		return this.repository.findAll();
	}
	
	@Transactional
	public void save(Paciente paciente) {
		this.repository.save(paciente);
	}
	
	@Transactional
	public void remove(Long id) {
		this.repository.delete(id);
	}
	
	@Transactional(readOnly=true)
	public Paciente getById(Long id) {
		return this.repository.findOne(id);
	}
	
	public List<Paciente> filtrar(PacientePesquisaDTO paciente) {
		String nome = paciente.getNome() == null ? "%" : paciente.getNome()+"%";
		return repository.findByNomeContaining(nome);
	}




}
