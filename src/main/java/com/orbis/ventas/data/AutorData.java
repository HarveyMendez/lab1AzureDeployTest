package com.orbis.ventas.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.orbis.ventas.domain.Autor;

@Repository
public class AutorData {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Autor> findAll(){
		String sqlSelect = "SELECT id_autor,nombre_autor,apellidos_autor FROM Autor";
				
		return jdbcTemplate.query(sqlSelect, new AutorExtractor());
	}
}
