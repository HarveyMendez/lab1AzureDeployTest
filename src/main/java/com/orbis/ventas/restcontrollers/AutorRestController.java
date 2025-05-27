package com.orbis.ventas.restcontrollers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orbis.ventas.business.AutorBusiness;
import com.orbis.ventas.domain.Autor;
import com.orbis.ventas.dto.AutorDTO;

@RestController
@RequestMapping(value = "/autores")
public class AutorRestController {
	
	@Autowired
	private AutorBusiness autorBusiness;
	
	@GetMapping
	public ResponseEntity<List<AutorDTO>> getAllAutores() {
		List<Autor> autores = autorBusiness.findAll();

		if (autores.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		List<AutorDTO> autorDTOs = autores.stream().map(this::convertToDTO).collect(Collectors.toList());

		return ResponseEntity.ok(autorDTOs);
	}
	
	private AutorDTO convertToDTO(Autor autor) {
		return new AutorDTO(autor.getId_autor(), autor.getNombre_autor(), autor.getApellidos_autor());
	}

}
