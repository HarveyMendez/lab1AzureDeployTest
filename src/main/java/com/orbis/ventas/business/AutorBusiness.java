package com.orbis.ventas.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orbis.ventas.data.AutorData;
import com.orbis.ventas.domain.Autor;

@Service
public class AutorBusiness {
	
	@Autowired
	private AutorData autorData;
	
	public List<Autor> findAll(){
		return this.autorData.findAll();
	}

}
