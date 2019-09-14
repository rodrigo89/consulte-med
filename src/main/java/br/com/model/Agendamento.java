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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message="Contato é obrigatório")
	private Contato contato;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotEmpty(message="Data agendamento é obrigatório")
	private Date dataAgendamento;
	
	
}
