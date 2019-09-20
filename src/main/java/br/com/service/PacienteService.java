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
	public List<Paciente> list(){
		return this.repository.findAll();
	}
	
	@Transactional(readOnly=true)
	public int qtd(){
		return this.repository.findAll().size();
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


	@Transactional
	public boolean ativarDesativar(Long id) {
		
		
		boolean ativou = false;
		
		Paciente paciente = this.repository.getOne(id);
		if(paciente.isAtivo()) {
			paciente.setAtivo(false);
			return ativou;
		}else {
			paciente.setAtivo(true);
			ativou = true;
		}
		return ativou;
	}
	
	public boolean ativaDesativarPaciente(Paciente paciente) {
		if (paciente.isAtivo()) {
			ativaDesativaUsuario(paciente);
		} else {
			ativaDesativaUsuario(paciente);
		}
		return false;
	}


	
	@Transactional
	private void ativaDesativaUsuario(Paciente paciente) {

		if (paciente.isAtivo()) {
			paciente.setAtivo(false);
		} else {
			paciente.setAtivo(true);
		}

		this.repository.saveAndFlush(paciente);
	}

	public void editar(Paciente c) {
		this.repository.saveAndFlush(c);
	}

}
