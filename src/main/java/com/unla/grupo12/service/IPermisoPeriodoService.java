package com.unla.grupo12.service;

import java.util.List;

import com.unla.grupo12.entity.PermisoPeriodo;
import com.unla.grupo12.model.PermisoPeriodoModel;


public interface IPermisoPeriodoService {
	
	PermisoPeriodoModel agregar(PermisoPeriodoModel permisoPeriodoModel);

	List<PermisoPeriodo> getAll();

	PermisoPeriodoModel findById(long id);
	
	List<PermisoPeriodoModel> listPermisoPeriodo();
}
