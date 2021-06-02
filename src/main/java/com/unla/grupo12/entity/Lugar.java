package com.unla.grupo12.entity;


import javax.persistence.*;

@Entity
@Table(name = "lugar")
public class Lugar {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String lugar;
  private String codigoPostal;

  public Lugar(int idLugar, String lugar, String codigoPostal) {
    this.id = idLugar;
    this.lugar = lugar;
    this.codigoPostal = codigoPostal;
  }

  public Lugar() {
  }

  public long getId() {
    return id;
  }

  public String getLugar() {
    return lugar;
  }

  public String getCodigoPostal() {
    return codigoPostal;
  }

  public void setId(long idLugar) {
    this.id = idLugar;
  }

  public void setLugar(String lugar) {
    this.lugar = lugar;
  }

  public void setCodigoPostal(String codigoPostal) {
    this.codigoPostal = codigoPostal;
  }

}
