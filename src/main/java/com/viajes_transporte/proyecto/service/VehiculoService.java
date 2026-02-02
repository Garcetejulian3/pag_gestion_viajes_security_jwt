package com.viajes_transporte.proyecto.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viajes_transporte.proyecto.dto.vehiculoDTO.CreateVehiculoDTO;
import com.viajes_transporte.proyecto.dto.vehiculoDTO.ResponseVehiculoDTO;
import com.viajes_transporte.proyecto.models.Vehiculo;
import com.viajes_transporte.proyecto.repository.VehiculoRepository;
import com.viajes_transporte.proyecto.service.inpl.IVehiculoService;

@Service
public class VehiculoService implements IVehiculoService{

    @Autowired
    private VehiculoRepository vehiculoRepo;

    @Override
    public Set<ResponseVehiculoDTO> allVehiculo() {
        List<Vehiculo> vehiculosList = vehiculoRepo.findAll();

        return vehiculosList.stream()
            .map(vehiculo -> new ResponseVehiculoDTO(
                vehiculo.getIdVehiculo(),
                vehiculo.getModelo(),
                vehiculo.getCodigoIdent(),
                vehiculo.getEstado()
            ))
            .collect(Collectors.toSet());
    }

    @Override
    public ResponseVehiculoDTO getVehiculo(Long id) {
        Vehiculo vehiculo = vehiculoRepo.findById(id)
            .orElseThrow(()-> new RuntimeException("Vehiculo no encontrado"));

        return new ResponseVehiculoDTO(
            vehiculo.getIdVehiculo(),
            vehiculo.getModelo(),
            vehiculo.getCodigoIdent(),
            vehiculo.getEstado()
        );
    }

    @Override
    public ResponseVehiculoDTO createVehiculo(CreateVehiculoDTO vehiculoDTO) {
        Vehiculo vehiculo = new Vehiculo(
            null,
            vehiculoDTO.getModelo(),
            vehiculoDTO.getCodigoIdent(),
            true
        );
        Vehiculo guardado = vehiculoRepo.save(vehiculo);
        return new ResponseVehiculoDTO(
            guardado.getIdVehiculo(),
            guardado.getModelo(),
            guardado.getCodigoIdent(),
            guardado.getEstado()
        );
    }

    @Override
    public void deleteVehiculo(Long id) {
        Vehiculo vehiculo = vehiculoRepo.findById(id)
        .orElseThrow(()-> new RuntimeException("No se encontro al vehiculo"));

        vehiculo.setEstado(false);
        vehiculoRepo.save(vehiculo);
    }

    @Override
    public ResponseVehiculoDTO editVehiculo(Long idVehiculo,CreateVehiculoDTO vehiculoDTO) {
        Vehiculo vehiculo = vehiculoRepo.findById(idVehiculo)
            .orElseThrow(()-> new RuntimeException("Vehiculo no encontrado "));

        vehiculo.setModelo(vehiculoDTO.getModelo());
        vehiculo.setCodigoIdent(vehiculoDTO.getCodigoIdent());
        vehiculo.setEstado(vehiculoDTO.getEstado());

        Vehiculo guardado = vehiculoRepo.save(vehiculo);

        return new ResponseVehiculoDTO(
            guardado.getIdVehiculo(),
            guardado.getModelo(),
            guardado.getCodigoIdent(),
            guardado.getEstado()
        );
    }

}
