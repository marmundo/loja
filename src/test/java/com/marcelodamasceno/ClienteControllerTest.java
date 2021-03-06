package com.marcelodamasceno;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.marcelodamasceno.controllers.ClienteController;
import com.marcelodamasceno.services.ClienteServiceImpl;






	@WebMvcTest(ClienteController.class) 
	public class ClienteControllerTest {
		@Autowired
		private MockMvc mockMvc;
		
		@MockBean
		private ClienteServiceImpl service;
		
		@Test
		public void testHomePage() throws Exception {
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"))
		.andExpect(content().string( containsString("Inicial")));
		}
	}

