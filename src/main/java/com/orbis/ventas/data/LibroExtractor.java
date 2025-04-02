package com.orbis.ventas.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.orbis.ventas.domain.Editorial;
import com.orbis.ventas.domain.Libro;

public class LibroExtractor implements ResultSetExtractor<List<Libro>>{
	@Override
	public List<Libro> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Integer, Libro> map = new HashMap<Integer, Libro>();
		Libro libro = null;
		while (rs.next()) {
			Integer id = rs.getInt("id_libro");
			libro = map.get(id);
			if (libro == null) {
				libro = new Libro();
				libro.setEditorial(new Editorial());
				libro.getEditorial().setId_editorial(rs.getInt("id_editorial"));
				libro.setId_libro(rs.getInt("id_libro"));
				libro.setTitulo(rs.getString("titulo"));
				libro.setAno(rs.getInt("ano"));
				libro.setPrecio(rs.getFloat("precio"));
				map.put(id, libro);
			}
		} // while
		return new ArrayList<Libro>(map.values());
	}

}
