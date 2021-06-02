package com.unla.grupo12.service;

import java.util.List;

import com.unla.grupo12.entity.Permiso;
import com.unla.grupo12.model.PermisoModel;



public interface IPermisoService {
	PermisoModel agregar(PermisoModel permisoModel);

	List<Permiso> getAll();

	PermisoModel findById(Long id);
	
	List<PermisoModel> listPermiso();

}
