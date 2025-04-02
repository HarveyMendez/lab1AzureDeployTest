package com.orbis.ventas.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.orbis.ventas.domain.Editorial;
import com.orbis.ventas.domain.Libro;

@Repository
public class LibroData {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Libro> buscarPorEditorial(int id){
		String sqlSelect = "SELECT id_libro, titulo, ano, precio, id_editorial FROM Libro WHERE id_editorial = ?";
				
		return jdbcTemplate.query(sqlSelect, new LibroExtractor(), id);
	}
}
