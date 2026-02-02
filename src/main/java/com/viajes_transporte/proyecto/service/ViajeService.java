package com.viajes_transporte.proyecto.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viajes_transporte.proyecto.dto.viajesDTO.CreateViaje;
import com.viajes_transporte.proyecto.dto.viajesDTO.ResponseViaje;
import com.viajes_transporte.proyecto.models.Chofer;
import com.viajes_transporte.proyecto.models.Vehiculo;
import com.viajes_transporte.proyecto.models.Viaje;
import com.viajes_transporte.proyecto.repository.ChoferRepository;
import com.viajes_transporte.proyecto.repository.VehiculoRepository;
import com.viajes_transporte.proyecto.repository.ViajeRepository;
import com.viajes_transporte.proyecto.service.inpl.IViajeService;

@Service
public class ViajeService implements IViajeService{

    @Autowired
    private ViajeRepository viajeRepo;

    @Autowired
    private ChoferRepository choferRepo;

    @Autowired
    private VehiculoRepository vehiculoRepo;

    @Override
    public Set<ResponseViaje> allViaje() {
        List<Viaje> viajesDB = viajeRepo.findAll();

        return viajesDB.stream()
            .map(viaje -> new ResponseViaje(
                viaje.getIdViajes(),
                viaje.getDestino(),
                viaje.getFechaSalida(),
                viaje.getChofer().getNombre() + " " + viaje.getChofer().getApellido(),
                viaje.getVehiculo().getModelo()
            ))
            .collect(Collectors.toSet());
    }

    @Override
    public ResponseViaje getViaje(Long id) {
        Viaje viaje = viajeRepo.findById(id)
            .orElseThrow(()-> new RuntimeException("Viaje no encontrado "));

        return new ResponseViaje(
            viaje.getIdViajes(),
            viaje.getDestino(),
            viaje.getFechaSalida(),
            viaje.getChofer().getNombre() + " " + viaje.getChofer().getApellido(),
            viaje.getVehiculo().getModelo()
        );
    }

        @Override
        public ResponseViaje createViaje(CreateViaje viajeDTO) {
            Viaje viaje = new Viaje();

            viaje.setDestino(viajeDTO.getDestino());
            viaje.setFechaSalida(LocalDateTime.now());

            Chofer choferBD = choferRepo.findById(viajeDTO.getIdChofer())
            .orElseThrow(()-> new RuntimeException("Chofer no encontrado"));
            viaje.setChofer(choferBD);

            Vehiculo vehiculo = vehiculoRepo.findById(viajeDTO.getIdVehiculo())
                .orElseThrow(()-> new RuntimeException("Vehiculo no encontrado"));
            viaje.setVehiculo(vehiculo);

            Viaje guardado = viajeRepo.save(viaje);

            return new ResponseViaje(
                guardado.getIdViajes(),
                guardado.getDestino(),
                guardado.getFechaSalida(),
                guardado.getChofer().getNombre() + " " + guardado.getChofer().getApellido(),
                guardado.getVehiculo().getModelo()
            );


        }

    @Override
    public void deleteViaje(Long id) {
        viajeRepo.deleteById(id);
    }

    @Override
    public ResponseViaje editViaje(Long idViaje,CreateViaje viajeDTO) {
        Viaje viaje = viajeRepo.findById(idViaje)
            .orElseThrow(()-> new RuntimeException("Viaje no encontrado "));

        viaje.setDestino(viajeDTO.getDestino());

        Chofer choferBD = choferRepo.findById(viajeDTO.getIdChofer())
        .orElseThrow(()-> new RuntimeException("Chofer no encontrado"));
        viaje.setChofer(choferBD);

        Vehiculo vehiculo = vehiculoRepo.findById(viajeDTO.getIdVehiculo())
            .orElseThrow(()-> new RuntimeException("Vehiculo no encontrado"));
        viaje.setVehiculo(vehiculo);

        Viaje guardado = viajeRepo.save(viaje);

        return new ResponseViaje(
            guardado.getIdViajes(),
            guardado.getDestino(),
            guardado.getFechaSalida(),
            guardado.getChofer().getNombre() + " " + guardado.getChofer().getApellido(),
            guardado.getVehiculo().getModelo()
        );

        
    }
}
