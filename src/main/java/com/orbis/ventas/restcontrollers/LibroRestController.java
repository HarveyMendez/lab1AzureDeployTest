package com.orbis.ventas.restcontrollers;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orbis.ventas.business.LibroBusiness;
import com.orbis.ventas.domain.Editorial;
import com.orbis.ventas.domain.Libro;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/libros")
public class LibroRestController {
	
	@Autowired
	private LibroBusiness libroBusiness;
	
	@PostMapping
	public ResponseEntity<?> createLibro(@RequestBody Libro libro, BindingResult bindingResult){
		
		
		if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            List<String> errorMessages = errors.stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
		
		try {
			this.libroBusiness.insertLibro(libro);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(libro, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Libro>> getAllLibros(){
		List<Libro> libros = libroBusiness.listarLibros();

		if (libros.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

//		List<LibroDTO> librosDTO = libros.stream().map(this::convertToDTO).collect(Collectors.toList());

		return ResponseEntity.ok(libros);
	}

}
