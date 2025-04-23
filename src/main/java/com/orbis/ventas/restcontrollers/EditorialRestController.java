package com.orbis.ventas.restcontrollers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orbis.ventas.business.EditorialBusiness;
import com.orbis.ventas.domain.Autor;
import com.orbis.ventas.domain.Editorial;
import com.orbis.ventas.dto.AutorDTO;
import com.orbis.ventas.dto.EditorialDTO;

@RestController
@RequestMapping(value = "/editoriales")
public class EditorialRestController {
	
	@Autowired
	private EditorialBusiness editorialBusiness;
	
	@GetMapping
	public ResponseEntity<List<EditorialDTO>> getAllEditoriales() {
		List<Editorial> editoriales = editorialBusiness.listarEditoriales();

		if (editoriales.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		List<EditorialDTO> editorialesDTO = editoriales.stream().map(this::convertToDTO).collect(Collectors.toList());

		return ResponseEntity.ok(editorialesDTO);
	}
	
	private EditorialDTO convertToDTO(Editorial editorial) {
		return new EditorialDTO(editorial.getId_editorial(), editorial.getNombre());
	}

}
