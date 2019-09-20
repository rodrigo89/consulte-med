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

import br.com.model.Agendamento;
import br.com.model.dto.AgendamentoPesquisaDTO;
import br.com.service.AgendamentoService;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoController {
	
	private static final String PAGES_CONTATO_NOVO_CONTATO = "pages/agendamento/novo_agendamento";

	private static final String PAGES_CONTATO_CONTATOS = "pages/agendamento/agendamentos";
	
	@Autowired
	private AgendamentoService service;
	
	@GetMapping
	public ModelAndView listar(@ModelAttribute("filtro") AgendamentoPesquisaDTO filtro) {
		ModelAndView mv = new ModelAndView(PAGES_CONTATO_CONTATOS);		
		
		if(!StringUtils.isEmpty(filtro.getNome())) {
			List<Agendamento> agendamentos = this.service.filtrar(filtro);
			mv.addObject("agendamentos", agendamentos);
			
		}else {
			mv.addObject("agendamentos", service.list());
		}
		
		return mv;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView excluir(@PathVariable Long id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/agendamentos");
		this.service.remove(id);
		attributes.addFlashAttribute("removido", "Agendamento removido com sucesso!");
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		Agendamento agendamento = this.service.getById(id); 
		return novo(agendamento);
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Agendamento agendamento) {
		ModelAndView mv = new ModelAndView(PAGES_CONTATO_NOVO_CONTATO);
		mv.addObject("agendamento", agendamento);
		return mv;
	}
	
	@GetMapping("/ativar/{id}")
	public ModelAndView ativarDesativar(@PathVariable("id") Long id,RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/agendamentos");
		
		if(this.service.ativarDesativar(id)) {
			attributes.addFlashAttribute("ativadoDesativado", "Agendamento ativado com sucesso!");
		}else {
			attributes.addFlashAttribute("ativadoDesativado", "Agendamento desativado com sucesso!");
		}
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView salvar(@Valid Agendamento agendamento, BindingResult result, Model model, RedirectAttributes attributes){
		ModelAndView mv = new ModelAndView("redirect:/agendamentos");
		
		if (result.hasErrors()) {
			return novo(agendamento);
		}

		attributes.addFlashAttribute("mensagem", "Agendamento salvo com sucesso");
		this.service.save(agendamento);
		return mv;
	}

}