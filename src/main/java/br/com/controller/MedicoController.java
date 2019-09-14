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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.model.Medico;
import br.com.service.MedicoService;

/**
 * @author carlosbarbosagomesfilho
 * Aug 30, 2019
 * 
 */
@Controller
@RequestMapping("/medicos")
public class MedicoController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MedicoService service;
	
	@GetMapping("/novo")
	public ModelAndView novo(Medico medico) {
		ModelAndView mv = new ModelAndView("medicos/add");
		mv.addObject("medico", medico);
		return mv;
	}
	
	@GetMapping("/list")
	public ModelAndView lista(){
		ModelAndView mv = new ModelAndView("medicos/list");
		mv.addObject("medicos", this.service.lista());
		return mv;
	}
	
	@PostMapping("/save")		
	public ModelAndView salva(@Valid Medico medico, BindingResult result, Model model, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/medicos/list");
		
		if (result.hasErrors()) {
			return novo(medico);
		}
		
		attributes.addFlashAttribute("mensagem", "Contato salvo com sucesso");
		this.service.salva(medico);
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		return novo(this.service.getMedicoById(id));
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView excluir(@PathVariable Long id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/medicos/list");
		this.service.delete(id);
		attributes.addFlashAttribute("removido", "Paciente removido com sucesso!");
		return mv;
	}

}
