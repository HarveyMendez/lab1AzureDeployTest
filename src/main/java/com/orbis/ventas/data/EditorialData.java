package com.orbis.ventas.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.orbis.ventas.domain.Editorial;
import com.orbis.ventas.domain.Libro;

@Repository
public class EditorialData {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Transactional(readOnly=true)
	public List<Editorial> listarEditoriales() {
		
		String sqlSelect = 
				"SELECT id_editorial, nombre, direccion, telefono FROM Editorial";
				
		return jdbcTemplate.query(sqlSelect, new EditorialExtractor() );
	}
	
}
