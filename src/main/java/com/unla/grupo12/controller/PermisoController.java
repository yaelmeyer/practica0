package com.unla.grupo12.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo12.converter.LugarConverter2;
import com.unla.grupo12.converter.PermisoConverter;
import com.unla.grupo12.converter.PermisoDiarioConverter;
import com.unla.grupo12.converter.PermisoPeriodoConverter;
import com.unla.grupo12.converter.PersonaConverter;
import com.unla.grupo12.entity.Lugar;
import com.unla.grupo12.entity.Permiso;
import com.unla.grupo12.entity.PermisoDiario;
import com.unla.grupo12.entity.PermisoPeriodo;
import com.unla.grupo12.entity.Persona;
import com.unla.grupo12.entity.Rodado;
import com.unla.grupo12.entity.Usuario;
import com.unla.grupo12.helpers.ViewRouteHelper;
import com.unla.grupo12.model.LugarModel;
import com.unla.grupo12.model.PermisoDiarioModel;
import com.unla.grupo12.model.PermisoModel;
import com.unla.grupo12.model.PermisoPeriodoModel;
import com.unla.grupo12.service.ILugarService;
import com.unla.grupo12.service.IPermisoDiarioService;
import com.unla.grupo12.service.IPermisoPeriodoService;
import com.unla.grupo12.service.IPersonaService;
import com.unla.grupo12.service.IRodadoService;
import com.unla.grupo12.service.IUsuarioService;


@Controller
@RequestMapping("/permiso")
public class PermisoController {

	@Autowired
	@Qualifier("permisoDiarioService")
	private IPermisoDiarioService permisoDiarioService;
	
	@Autowired
	@Qualifier("permisoPeriodoService")
	private IPermisoPeriodoService permisoPeriodoService;
	
	@Autowired
	@Qualifier("personaService")
	private IPersonaService personaService;
	
	@Autowired
	@Qualifier("rodadoService")
	private IRodadoService rodadoService;
	
	@Autowired  
	@Qualifier("lugarService")
	private ILugarService lugarService;
	
	@Autowired
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter;
	
	@Autowired
	@Qualifier("lugarConverter2")
	private LugarConverter2 lugarConverter;
	
	@Autowired
	@Qualifier("permisoDiarioConverter")
	private PermisoDiarioConverter permisoDiarioConverter;
	
	@Autowired  
	@Qualifier("permisoPeriodoConverter")
	private PermisoPeriodoConverter permisoPeriodoConverter;
	
	@Autowired  
	@Qualifier("permisoConverter")
	private PermisoConverter permisoConverter;
	
	@Autowired
	@Qualifier("usuarioService")
	private IUsuarioService usuarioService;
	
	private LocalDate fecha = null;
	
	//*********************************fechas******************************
	@GetMapping("/pedirfecha")
	public ModelAndView pedirFecha() {
		ModelAndView modelo = new ModelAndView(ViewRouteHelper.PEDIR_FECHA);			

		modelo.addObject("fecha", new Date());
				
		return modelo;
	}
	
	@PostMapping("/agregarfecha")
	public RedirectView agregarPermisoDiario(@ModelAttribute("fecha") Date fecha) {
		RedirectView redirect = new RedirectView("/permiso/tipopermiso", false);
		
		LocalDate fechaNueva = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	
		this.fecha = fechaNueva;
		
		return redirect;			
	}

	//*****************************************PERMISO**************************
	//@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/tipopermiso")
	public ModelAndView tipoPermiso() {
		ModelAndView modelo = new ModelAndView(ViewRouteHelper.TIPO_PERMISO);
		
		String tipo="";
		modelo.addObject("tipo", tipo);
		
		return modelo;
	}
	
	//@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/pedidodiario")
	public ModelAndView formPermisoDiario() {
		ModelAndView modelo = new ModelAndView(ViewRouteHelper.PERMISO_DIARIO_AGREGAR);			
		
		List<Persona> personas = personaService.getAll();		
		List<Lugar> lugares = lugarService.getAll();
		
		modelo.addObject("personas", personas);		
		modelo.addObject("lugares", lugares);			
		modelo.addObject("permiso", new PermisoDiario());
		
		return modelo;
	}
	
	
	//@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/pedidoperiodo")
	public ModelAndView formPermisoPeriodo() {
		ModelAndView modelo = new ModelAndView(ViewRouteHelper.PERMISO_PERIODO_AGREGAR);
		
		List<Persona> personas = personaService.getAll();
		List<Rodado> rodados = rodadoService.getAll();
		List<Lugar> lugares = lugarService.getAll();
		
		modelo.addObject("personas", personas);
		modelo.addObject("rodados", rodados);
		modelo.addObject("lugares", lugares);
		modelo.addObject("permiso", new PermisoPeriodo());
		
		return modelo;
	}
	
	//@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/agregarpermisodiario")
	public RedirectView agregarPermisoDiario(@ModelAttribute("permiso") PermisoDiarioModel permisoDiarioModel) {
		RedirectView redirect = new RedirectView("/lista-permisos", false);
		
		permisoDiarioModel.setFecha(this.fecha);
			
		long idPersona= permisoDiarioModel.getPedido().getId();	
		permisoDiarioModel.setPedido(personaService.findById(idPersona));
		
		List<LugarModel> listLugar = permisoDiarioModel.getDesdeHasta();
		List<LugarModel> nuevaListLugar= new ArrayList<LugarModel>();
		for (LugarModel l : listLugar)
			nuevaListLugar.add(lugarService.findById(l.getIdLugar()));
		
		permisoDiarioModel.setDesdeHasta(nuevaListLugar);
		permisoDiarioService.agregar(permisoDiarioModel );
		
		return redirect;			
	}
	
	//@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/agregarpermisoPeriodo")
	public RedirectView agregarPermisoPeriodo(@ModelAttribute("permiso") PermisoPeriodoModel permisoPeriodoModel) {
		RedirectView redirect = new RedirectView("/lista-permisos", false);
		
		
		
		long idPersona = permisoPeriodoModel.getPedido().getId();		
		permisoPeriodoModel.setPedido(personaService.findById(idPersona));
		
		long idRodado = permisoPeriodoModel.getRodado().getIdRodado();
		permisoPeriodoModel.setRodado(rodadoService.findById(idRodado));
		
		List<LugarModel> listLugar = permisoPeriodoModel.getDesdeHasta();
		List<LugarModel> nuevaListLugar= new ArrayList<LugarModel>();
		for (LugarModel l : listLugar)
			nuevaListLugar.add(lugarService.findById(l.getIdLugar()));
		
		permisoPeriodoModel.setDesdeHasta(nuevaListLugar);
		permisoPeriodoService.agregar(permisoPeriodoModel );
		
		return redirect;			
	}


}


