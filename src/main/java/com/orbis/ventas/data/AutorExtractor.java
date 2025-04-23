package com.orbis.ventas.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.orbis.ventas.domain.Autor;
import com.orbis.ventas.domain.Editorial;
import com.orbis.ventas.domain.Libro;


public class AutorExtractor implements ResultSetExtractor<List<Autor>>{
	@Override
	public List<Autor> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Integer, Autor> map = new HashMap<Integer, Autor>();
		Autor autor = null;
		while (rs.next()) {
			Integer id = rs.getInt("id_autor");
			autor = map.get(id);
			if (autor == null) {
				autor = new Autor();
				autor.setId_autor(rs.getInt("id_autor"));
				autor.setNombre_autor(rs.getString("nombre_autor"));
				autor.setApellidos_autor(rs.getString("apellidos_autor"));

				map.put(id, autor);
			}
		} // while
		return new ArrayList<Autor>(map.values());
	}
}
