/**
 * 
 */
package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.model.Agendamento;
import br.com.model.dto.AgendamentoPesquisaDTO;
import br.com.repository.AgendamentoRepository;

/**
 * @author carlosbarbosagomesfilho
 *
 */
@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository repository;
	
	@Transactional(readOnly=true)
	public List<Agendamento> list(){
		return this.repository.findAll();
	}
	
	@Transactional(readOnly=true)
	public int qtd(){
		return this.repository.findAll().size();
	}
	
	@Transactional
	public void save(Agendamento agendamento) {
		this.repository.save(agendamento);
	}
	
	@Transactional
	public void remove(Long id) {
		this.repository.delete(id);
	}
	
	@Transactional(readOnly=true)
	public Agendamento getById(Long id) {
		return this.repository.findOne(id);
	}
	
	public List<Agendamento> filtrar(AgendamentoPesquisaDTO agendamento) {
		Long Id = agendamento.getId() == null ? null : agendamento.getId();
		return repository.findByIdContaining(Id);
	}


	public void editar(Agendamento c) {
		this.repository.saveAndFlush(c);
	}

}
