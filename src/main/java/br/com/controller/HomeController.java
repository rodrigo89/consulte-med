package br.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.service.AgendamentoService;
import br.com.service.ContatoService;
import br.com.service.MedicoService;
import br.com.service.PacienteService;

@Controller
@RequestMapping
public class HomeController {
	


	@Autowired
	private PacienteService servicePac;
	@Autowired
	private ContatoService serviceCon;
	@Autowired
	private MedicoService serviceMed;
	@Autowired
	private AgendamentoService serviceAge;

	@GetMapping("/")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("pages/home");
		int qtd = this.servicePac.qtd();
		mv.addObject("pacientes_qtd", qtd);
		qtd = this.serviceCon.qtd();
		mv.addObject("contatos_qtd", qtd);
		qtd = this.serviceMed.qtd();
		mv.addObject("medicos_qtd", qtd);
		qtd = this.serviceAge.qtd();
		mv.addObject("agendamentos_qtd", qtd);
		return mv;
	}
}
