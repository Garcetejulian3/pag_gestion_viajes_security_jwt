package com.viajes_transporte.proyecto.service.inpl;

import java.util.Set;

import com.viajes_transporte.proyecto.dto.viajesDTO.CreateViaje;
import com.viajes_transporte.proyecto.dto.viajesDTO.ResponseViaje;

public interface IViajeService {

    // metodo para listar los Viaje
    Set<ResponseViaje>allViaje();
    // metodo para traer uno en especifico
    ResponseViaje getViaje(Long id);
    // metodo para crear un Viaje
    ResponseViaje createViaje(CreateViaje viajeDTO);
    // metodo para eliminar un Viaje
    void deleteViaje(Long id);
    // metodo para editar un Viaje
    ResponseViaje editViaje(Long idViaje,CreateViaje viajeDTO);

}
