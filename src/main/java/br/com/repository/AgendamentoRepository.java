/**
 * 
 */
package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.model.Agendamento;


/**
 * @author carlosbarbosagomesfilho
 * Aug 30, 2019
 * 
 */
@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

	public List<Agendamento> findByNomeContaining(String nome);
}
