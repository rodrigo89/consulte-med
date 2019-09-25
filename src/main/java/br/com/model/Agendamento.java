/**
 * 
 */
package br.com.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author carlosbarbosagomesfilho
 * Sep 13, 2019
 * 
 */

@NoArgsConstructor
@EqualsAndHashCode
@Data
@Entity
@Table(name="agendamentos")
public class Agendamento implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@NotEmpty(message="Paciente é obrigatório")
	private Paciente paciente;
	
	
	@NotEmpty(message="Médico é obrigatório")
	private Medico medico;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Paciente getPaciente() {
		return paciente;
	}


	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}


	public Medico getMedico() {
		return medico;
	}


	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	

	
}
