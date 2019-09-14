/**
 * 
 */
package br.com.controller;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.model.Agendamento;
import br.com.model.Contato;
import br.com.service.AgendamentoService;

/**
 * @author carlosbarbosagomesfilho
 * Sep 13, 2019
 * 
 */

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoController implements Serializable {

	
	@Autowired
	private AgendamentoService service;
	
	
	@GetMapping
	public ModelAndView agendamentos() {
		ModelAndView mv = new ModelAndView("pages/agendamento/agendamentos");
		mv.addObject("agendamentos", this.service.list());
		return mv;
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Agendamento agendamento) {
		ModelAndView mv = new ModelAndView("pages/agendamento/agendamento");
		mv.addObject("agendamento", agendamento);
		return mv;
	}
	
	@PostMapping("/agendamentos")
	public ModelAndView salvar(@Valid Agendamento agendamento, BindingResult result, Model model, RedirectAttributes attributes){
		ModelAndView mv = new ModelAndView("redirect:/contatos");
		
		if (result.hasErrors()) {
			return novo(agendamento);
		}

		attributes.addFlashAttribute("mensagem", "Contato salvo com sucesso");
		this.service.save(agendamento);
		return mv;
	}
}
