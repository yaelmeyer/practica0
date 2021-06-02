package com.unla.grupo12.converter;

import com.unla.grupo12.entity.Lugar;
import com.unla.grupo12.model.LugarModel;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component("lugarConverter")
public class LugarConverter {

  public Set<LugarModel> listLugarModel(Set<Lugar> listLugar) {

    Set<LugarModel> lugarModelList = new HashSet<>();

    for (Lugar l : listLugar) {
      LugarModel lugarModel = new LugarModel();
      lugarModel.setIdLugar((int)l.getId());
      lugarModel.setCodigoPostal(l.getCodigoPostal());
      lugarModel.setLugar(l.getLugar());
      lugarModelList.add(lugarModel);
    }
    return lugarModelList;
  }

  public Set<Lugar> listLugar(Set<LugarModel> listLugar) {

    Set<Lugar> lugarList = new HashSet<>();

    for (LugarModel l : listLugar) {
      Lugar lugar = new Lugar();
      lugar.setId(l.getIdLugar());
      lugar.setCodigoPostal(l.getCodigoPostal());
      lugar.setLugar(l.getLugar());
      lugarList.add(lugar);
    }
    return lugarList;
  }

  public Lugar modelToEntity(LugarModel lugarModel) {

    return new Lugar(lugarModel.getIdLugar(), lugarModel.getCodigoPostal(), lugarModel.getLugar());
  }

  public LugarModel entityToModel(Lugar lugar) {

    return new LugarModel((int)lugar.getId(), lugar.getCodigoPostal(), lugar.getLugar());
  }


}
