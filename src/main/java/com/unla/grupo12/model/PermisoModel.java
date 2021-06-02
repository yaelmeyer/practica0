package com.unla.grupo12.model;

import java.time.LocalDate;
import java.util.List;

public class PermisoModel {

  protected int idPermiso;
  protected PersonaModel pedido;
  protected LocalDate fecha;
  protected List<LugarModel> desdeHasta;

  public PermisoModel(int idPermiso, PersonaModel pedido, LocalDate fecha, List<LugarModel> desdeHasta) {
    this.idPermiso = idPermiso;
    this.pedido = pedido;
    this.fecha = fecha;
    this.desdeHasta = desdeHasta;
  }

  public PermisoModel() {
  }

  public int getIdPermiso() {
    return idPermiso;
  }

  public void setIdPermiso(int idPermiso) {
    this.idPermiso = idPermiso;
  }

  public PersonaModel getPedido() {
    return pedido;
  }

  public void setPedido(PersonaModel pedido) {
    this.pedido = pedido;
  }

  public LocalDate getFecha() {
    return fecha;
  }

  public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
  }

  public List<LugarModel> getDesdeHasta() {
    return desdeHasta;
  }

  public void setDesdeHasta(List<LugarModel> desdeHasta) {
    this.desdeHasta = desdeHasta;
  }

  @Override
  public String toString() {
    return "PermisoModel{" +
        "idPermiso=" + idPermiso +
        ", pedido=" + pedido +
        ", fecha=" + fecha +
        ", desdeHasta=" + desdeHasta +
        '}';
  }
}
