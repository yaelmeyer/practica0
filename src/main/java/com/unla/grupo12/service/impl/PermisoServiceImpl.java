package com.unla.grupo12.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo12.converter.PermisoConverter;
import com.unla.grupo12.entity.Permiso;
import com.unla.grupo12.model.PermisoModel;
import com.unla.grupo12.repository.IPermisoRepository;
import com.unla.grupo12.service.IPermisoService;

@Service("permisoService")
public class PermisoServiceImpl implements IPermisoService{

	@Autowired
	@Qualifier("permisoRepository")
	private IPermisoRepository permisoRepository; 
	
	@Autowired
	@Qualifier("permisoConverter")
	private PermisoConverter permisoConverter;
	
	@Override
	public PermisoModel agregar(PermisoModel permisoModel) {
		Permiso permiso = permisoRepository.save(permisoConverter.modelToEntity(permisoModel)); 
		return permisoConverter.entityToModel(permiso);
	}

	@Override
	public List<Permiso> getAll() {
		return permisoRepository.findAll();
	}


	@Override
	public List<PermisoModel> listPermiso() {
		return permisoConverter.listPermisoModel(permisoRepository.findAll());
	}

	@Override
	public PermisoModel findById(Long id) {
		PermisoModel permisoModel = null;
		Optional<Permiso> resultado = permisoRepository.findById(id) ;
		if(resultado.isPresent())
			permisoModel = permisoConverter.entityToModel(resultado.get());
		
		return permisoModel;
	}

	

}
