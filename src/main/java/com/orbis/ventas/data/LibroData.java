package com.orbis.ventas.data;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.orbis.ventas.domain.Autor;
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
	
	@Transactional
	public void insertLibro(Libro libro) throws SQLException{
		SimpleJdbcCall simpleJdbcCallPelicula = new SimpleJdbcCall(jdbcTemplate).
				withCatalogName("dbo").
				withProcedureName("InsertLibro").withoutProcedureColumnMetaDataAccess().
				declareParameters(new SqlOutParameter("@id_libro", Types.INTEGER)).
				declareParameters(new SqlParameter("@titulo", Types.VARCHAR)).
				declareParameters(new SqlParameter("@ano", Types.SMALLINT)).
				declareParameters(new SqlParameter("@precio", Types.FLOAT)).
				declareParameters(new SqlParameter("@id_editorial", Types.INTEGER));
		Map<String, Object> outParameters = simpleJdbcCallPelicula.execute(libro.getTitulo(), libro.getAno(), libro.getPrecio(), libro.getEditorial().getId_editorial());
		libro.setId_libro(Integer.parseInt(outParameters.get("@id_libro").toString()));
		
		SimpleJdbcCall simpleJdbcCallPeliculaActor = new SimpleJdbcCall(jdbcTemplate).
				withCatalogName("dbo").
				withProcedureName("InsertLibroAutor").withoutProcedureColumnMetaDataAccess().
				declareParameters(new SqlParameter("@id_libro", Types.INTEGER)).
				declareParameters(new SqlParameter("@id_autor", Types.INTEGER));
		
		for(Autor autor:libro.getAutores())
			simpleJdbcCallPeliculaActor.execute(libro.getId_libro(), autor.getId_autor());
	}
	
	public List<Libro> listarLibros(){
		String sqlSelect = "SELECT id_libro, titulo, ano, precio, id_editorial FROM Libro";
		
		return jdbcTemplate.query(sqlSelect, new LibroExtractor());
	}
}
