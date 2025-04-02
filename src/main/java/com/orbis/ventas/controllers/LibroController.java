package com.orbis.ventas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.orbis.ventas.business.LibroBusiness;

@Controller
public class LibroController {

	@Autowired
	private LibroBusiness libroBusiness;

	@RequestMapping(value="/buscarPorEditorial", method=RequestMethod.GET)
	public String buscarLibros(Model model, @RequestParam("id_editorial") int id) {
	    model.addAttribute("libros", libroBusiness.buscarPorEditorial(id));
	    model.addAttribute("id_editorial", id);
	    return "mostrar_libros";
	}
}
