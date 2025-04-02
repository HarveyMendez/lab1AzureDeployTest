package com.orbis.ventas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.orbis.ventas.business.EditorialBusiness;

@Controller
public class EditorialController {
	
	@Autowired
	private EditorialBusiness editorialBusiness;
	
	@RequestMapping(value="/listarEditoriales", method=RequestMethod.GET )
	public String iniciar(Model model) {
		model.addAttribute("editoriales", editorialBusiness.listarEditoriales());
		return "ver_editoriales";
	}
}
