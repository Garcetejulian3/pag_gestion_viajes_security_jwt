package com.viajes_transporte.proyecto.service.inpl;

import java.util.Set;

import com.viajes_transporte.proyecto.dto.vehiculoDTO.CreateVehiculoDTO;
import com.viajes_transporte.proyecto.dto.vehiculoDTO.ResponseVehiculoDTO;

public interface IVehiculoService {

    // metodo para listar los vehiculo
    Set<ResponseVehiculoDTO>allVehiculo();
    // metodo para traer uno en especifico
    ResponseVehiculoDTO getVehiculo(Long id);
    // metodo para crear un vehiculo
    ResponseVehiculoDTO createVehiculo(CreateVehiculoDTO vehiculoDTO);
    // metodo para eliminar un vehiculo
    void deleteVehiculo(Long id);
    // metodo para editar un vehiculo
    ResponseVehiculoDTO editVehiculo(Long idVehiculo,CreateVehiculoDTO vehiculoDTO);

}
