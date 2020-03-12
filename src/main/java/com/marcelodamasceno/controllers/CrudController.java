package com.marcelodamasceno.controllers;

public interface CrudController {
	
	 void cria(Object object);
	 void remove(Long id);
	 void atualiza(Object object);

}
