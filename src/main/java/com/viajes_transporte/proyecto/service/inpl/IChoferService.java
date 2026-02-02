package com.viajes_transporte.proyecto.service.inpl;

import java.util.Set;

import com.viajes_transporte.proyecto.dto.choferDTO.CreateChoferDTO;
import com.viajes_transporte.proyecto.dto.choferDTO.ResponseChoferDTO;

public interface IChoferService {

    // metodo para listar los choferes
    Set<ResponseChoferDTO>allChofer();
    // metodo para traer uno en especifico
    ResponseChoferDTO getChofer(Long id);
    // metodo para crear un chofer
    ResponseChoferDTO createChofer(CreateChoferDTO choferDTO);
    // metodo para eliminar un chofer
    void deleteChofer(Long id);
    // metodo para editar un chofer
    ResponseChoferDTO editChofer(Long idChofer,CreateChoferDTO choferDTO);

}
