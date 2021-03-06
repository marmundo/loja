package com.marcelodamasceno;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.marcelodamasceno.model.Cliente;

@Controller
public class PaginaInicial {
	

	private final Cliente cliente;
	
	public PaginaInicial() {
		super();
		this.cliente = new Cliente();
	}

	
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/ambiente")
	public String ambiente() {
		return "ambiente";
	}
	
//	@GetMapping("/cliente/{primeiroNome}")
//	public ModelAndView olaCliente(@PathVariable("primeiroNome") String primeiroNome, Model model) {
//		cliente.setPrimeiroNome(primeiroNome);
//		model.addAttribute("cliente",cliente);
//		return new ModelAndView("ola_usuario","cliente",cliente);
//	}
	
	@GetMapping("/cliente/{primeiroNome}")
	public String olaClientePathVariable(@PathVariable("primeiroNome") String primeiroNome, Model model) {
		cliente.setPrimeiroNome(primeiroNome);
		model.addAttribute("cliente",cliente);
		return "ola_usuario";
	}
	
	@GetMapping("/cliente")
	public String olaClienteRequestParam(@RequestParam("primeiroNome") String primeiroNome, Model model) {
		cliente.setPrimeiroNome(primeiroNome);
		model.addAttribute("cliente",cliente);
		return "ola_usuario";
	}

}
