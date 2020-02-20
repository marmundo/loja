package com.marcelodamasceno;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaginaInicial {
	
	@GetMapping("/")
	public String home() {
		return "<h1>Olá Marcelo</h1>";
	}

}
