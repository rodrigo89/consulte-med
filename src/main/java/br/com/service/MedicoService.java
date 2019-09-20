/**
 * 
 */
package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.model.Medico;
import br.com.model.dto.MedicoPesquisaDTO;
import br.com.repository.MedicoRepository;

/**
 * @author carlosbarbosagomesfilho
 *
 */
@Service
public class MedicoService {

	@Autowired
	private MedicoRepository repository;
	
	@Transactional(readOnly=true)
	public List<Medico> list(){
		return this.repository.findAll();
	}
	
	@Transactional(readOnly=true)
	public int qtd(){
		return this.repository.findAll().size();
	}
	
	@Transactional
	public void save(Medico medico) {
		this.repository.save(medico);
	}
	
	@Transactional
	public void remove(Long id) {
		this.repository.delete(id);
	}
	
	@Transactional(readOnly=true)
	public Medico getById(Long id) {
		return this.repository.findOne(id);
	}
	
	public List<Medico> filtrar(MedicoPesquisaDTO medico) {
		String nome = medico.getNome() == null ? "%" : medico.getNome()+"%";
		return repository.findByNomeContaining(nome);
	}


	@Transactional
	public boolean ativarDesativar(Long id) {
		
		
		boolean ativou = false;
		
		Medico medico = this.repository.getOne(id);
		if(medico.isAtivo()) {
			medico.setAtivo(false);
			return ativou;
		}else {
			medico.setAtivo(true);
			ativou = true;
		}
		return ativou;
	}
	
	public boolean ativaDesativarMedico(Medico medico) {
		if (medico.isAtivo()) {
			ativaDesativaUsuario(medico);
		} else {
			ativaDesativaUsuario(medico);
		}
		return false;
	}


	
	@Transactional
	private void ativaDesativaUsuario(Medico medico) {

		if (medico.isAtivo()) {
			medico.setAtivo(false);
		} else {
			medico.setAtivo(true);
		}

		this.repository.saveAndFlush(medico);
	}

	public void editar(Medico c) {
		this.repository.saveAndFlush(c);
	}

}
