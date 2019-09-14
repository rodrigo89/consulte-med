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
	public String getPrimeiroNome() {
		return primeiroNome;
	}
	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}
	public String getSegundoNome() {
		return segundoNome;
	}
	public void setSegundoNome(String segundoNome) {
		this.segundoNome = segundoNome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
