/**
 * 
 */
package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.model.Medico;


/**
 * @author carlosbarbosagomesfilho
 * Aug 30, 2019
 * 
 */
@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

	public List<Medico> findByNomeContaining(String nome);
}
