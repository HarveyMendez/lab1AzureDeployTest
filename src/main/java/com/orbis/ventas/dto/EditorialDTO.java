package com.orbis.ventas.dto;

public class EditorialDTO {
	
	private int id_editorial;
	private String nombre;
	
	public EditorialDTO() {
		super();
	}

	public EditorialDTO(int id_editorial, String nombre) {
		super();
		this.id_editorial = id_editorial;
		this.nombre = nombre;
	}

	public int getId_editorial() {
		return id_editorial;
	}

	public void setId_editorial(int id_editorial) {
		this.id_editorial = id_editorial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
