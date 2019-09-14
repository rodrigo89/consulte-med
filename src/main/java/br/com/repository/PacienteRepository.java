/**
 * 
 */
package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.model.Contato;
import br.com.model.Paciente;


/**
 * @author carlosbarbosagomesfilho
 * Aug 30, 2019
 * 
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	public List<Paciente> findByNomeContaining(String nome);
}
