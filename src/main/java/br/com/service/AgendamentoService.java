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
		String nome = agendamento.getNome() == null ? "%" : agendamento.getNome()+"%";
		return repository.findByNomeContaining(nome);
	}


	@Transactional
	public boolean ativarDesativar(Long id) {
		
		
		boolean ativou = false;
		
		Agendamento agendamento = this.repository.getOne(id);
		if(agendamento.isAtivo()) {
			agendamento.setAtivo(false);
			return ativou;
		}else {
			agendamento.setAtivo(true);
			ativou = true;
		}
		return ativou;
	}
	
	public boolean ativaDesativarAgendamento(Agendamento agendamento) {
		if (agendamento.isAtivo()) {
			ativaDesativaUsuario(agendamento);
		} else {
			ativaDesativaUsuario(agendamento);
		}
		return false;
	}


	
	@Transactional
	private void ativaDesativaUsuario(Agendamento agendamento) {

		if (agendamento.isAtivo()) {
			agendamento.setAtivo(false);
		} else {
			agendamento.setAtivo(true);
		}

		this.repository.saveAndFlush(agendamento);
	}

	public void editar(Agendamento c) {
		this.repository.saveAndFlush(c);
	}

}
