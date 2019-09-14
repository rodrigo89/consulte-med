/**
 * 
 */
package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.model.Agendamento;
import br.com.model.dto.ContatoPesquisaDTO;
import br.com.repository.AgendamentosRepository;
import br.com.repository.ContatosRepository;

/**
 * @author carlosbarbosagomesfilho
 *
 */
@Service
public class AgendamentoService {

	@Autowired
	private AgendamentosRepository repository;
	
	@Transactional(readOnly=true)
	public List<Agendamento> list(){
		return this.repository.findAll();
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
	
	

}
