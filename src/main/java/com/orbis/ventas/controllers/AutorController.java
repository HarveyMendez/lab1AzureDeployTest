package com.orbis.ventas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.orbis.ventas.business.AutorBusiness;

@Controller
public class AutorController {
	
	@Autowired
	private AutorBusiness autorBusiness;
	
	@RequestMapping(value="/listarAutores", method=RequestMethod.GET )
	public String iniciar(Model model) {
		model.addAttribute("editoriales", autorBusiness.findAll());
		return "ver_editoriales";
	}

}
