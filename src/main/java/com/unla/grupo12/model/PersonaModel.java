package com.unla.grupo12.model;

public class PersonaModel {

  private long id;
  private String nombre;
  private String apellido;
  private long dni;

  public PersonaModel(long id, String nombre, String apellido, long dni) {
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.dni = dni;
  }

  public PersonaModel() {
  }

  public long getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public long getDni() {
    return dni;
  }

  public void setIdPersona(long idPersona) {
    this.id = idPersona;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public void setDni(long dni) {
    this.dni = dni;
  }

  @Override
  public String toString() {
    return "Persona{" +
        "idPersona=" + id +
        ", nombre='" + nombre + '\'' +
        ", apellido='" + apellido + '\'' +
        ", dni=" + dni +
        '}';
  }

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int)(this.id ^ (this.id >>> 32));
	return result;
}

@Override
public boolean equals(Object obj) {
	return this.id == ( ((PersonaModel)obj).getId() );
}
  
  
}
