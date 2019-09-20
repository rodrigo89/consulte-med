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

import br.com.model.Medico;
import br.com.model.dto.MedicoPesquisaDTO;
import br.com.service.MedicoService;



@Controller
@RequestMapping("/medicos")
public class MedicosController {
	
	private static final String PAGES_PACIENTE_NOVO_PACIENTE = "pages/medico/novo_medico";

	private static final String PAGES_PACIENTE_PACIENTES = "pages/medico/medicos";
	
	@Autowired
	private MedicoService service;
	
	
	
	@GetMapping
	public ModelAndView listar(@ModelAttribute("filtro") MedicoPesquisaDTO filtro) {
		ModelAndView mv = new ModelAndView(PAGES_PACIENTE_PACIENTES);		
		
		if(!StringUtils.isEmpty(filtro.getNome())) {
			List<Medico> medicos = this.service.filtrar(filtro);
			mv.addObject("medicos", medicos);
			
		}else {
			mv.addObject("medicos", service.list());
		}
		
		return mv;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView excluir(@PathVariable Long id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/medicos");
		this.service.remove(id);
		attributes.addFlashAttribute("removido", "Medico removido com sucesso!");
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		Medico medico = this.service.getById(id); 
		return novo(medico);
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Medico medico) {
		ModelAndView mv = new ModelAndView(PAGES_PACIENTE_NOVO_PACIENTE);
		mv.addObject("medico", medico);
		return mv;
	}
	
	
	@PostMapping("/save")
	public ModelAndView salvar(@Valid Medico medico, BindingResult result, Model model, RedirectAttributes attributes){
		ModelAndView mv = new ModelAndView("redirect:/medicos");
		
		if (result.hasErrors()) {
			return novo(medico);
		}

		attributes.addFlashAttribute("mensagem", "Medico salvo com sucesso");
		this.service.save(medico);
		return mv;
	}

}