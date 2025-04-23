package com.orbis.ventas.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orbis.ventas.data.EditorialData;
import com.orbis.ventas.data.LibroData;
import com.orbis.ventas.domain.Editorial;
import com.orbis.ventas.domain.Libro;

@Service
public class LibroBusiness {
	@Autowired
	private LibroData libroData;
	
	public List<Libro> buscarPorEditorial(int id){
		return this.libroData.buscarPorEditorial(id);
	}
	
	public void insertLibro(Libro libro) throws SQLException{
		this.libroData.insertLibro(libro);
	}
	
	public List<Libro> listarLibros(){
		return this.libroData.listarLibros();
	}

}
