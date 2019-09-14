package br.com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import br.com.model.Paciente;
import br.com.model.dto.PacientePesquisaDTO;
import br.com.service.PacienteService;



@Controller
@RequestMapping("/pacientes")
public class PacientesController {
	
	private static final String PAGES_PACIENTE_NOVO_PACIENTE = "pages/paciente/novo_paciente";

	private static final String PAGES_PACIENTE_PACIENTES = "pages/paciente/pacientes";
	
	@Autowired
	private PacienteService service;
	
	@GetMapping
	public ModelAndView listar(@ModelAttribute("filtro") PacientePesquisaDTO filtro) {
		ModelAndView mv = new ModelAndView(PAGES_PACIENTE_PACIENTES);		
		
		if(!StringUtils.isEmpty(filtro.getNome())) {
			List<Paciente> pacientes = this.service.filtrar(filtro);
			mv.addObject("pacientes", pacientes);
			
		}else {
			mv.addObject("pacientes", service.lista());
		}
		
		return mv;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView excluir(@PathVariable Long id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/pacientes");
		this.service.remove(id);
		attributes.addFlashAttribute("removido", "Paciente removido com sucesso!");
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		Paciente paciente = this.service.getById(id); 
		return novo(paciente);
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Paciente paciente) {
		ModelAndView mv = new ModelAndView(PAGES_PACIENTE_NOVO_PACIENTE);
		mv.addObject("paciente", paciente);
		return mv;
	}
	
	
	@PostMapping("/save")
	public ModelAndView salvar(@Valid Paciente paciente, BindingResult result, Model model, RedirectAttributes attributes){
		ModelAndView mv = new ModelAndView("redirect:/pacientes");
		
		if (result.hasErrors()) {
			return novo(paciente);
		}

		attributes.addFlashAttribute("mensagem", "Paciente salvo com sucesso");
		this.service.save(paciente);
		return mv;
	}

}