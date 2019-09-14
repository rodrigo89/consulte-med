/**
 * 
 */
package br.com.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author carlosbarbosagomesfilho
 * Sep 3, 2019
 * 
 */
@Getter
@Setter
public class Usuario implements Serializable {

	private String primeiroNome;
	private String segundoNome;
	private String email;
}
