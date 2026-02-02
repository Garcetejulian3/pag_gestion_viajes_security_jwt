package com.viajes_transporte.proyecto.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viajes_transporte.proyecto.dto.choferDTO.CreateChoferDTO;
import com.viajes_transporte.proyecto.dto.choferDTO.ResponseChoferDTO;
import com.viajes_transporte.proyecto.models.Chofer;
import com.viajes_transporte.proyecto.repository.ChoferRepository;
import com.viajes_transporte.proyecto.service.inpl.IChoferService;

@Service
public class ChoferService implements IChoferService {

    @Autowired
    private ChoferRepository choferRepo;

    @Autowired

    @Override
    public Set<ResponseChoferDTO> allChofer() {

        List<Chofer> choferesBD = choferRepo.findAll();

        return choferesBD.stream()
                .map(chofer -> new ResponseChoferDTO(
                        chofer.getIdChofer(),
                        chofer.getNombre(),
                        chofer.getApellido(),
                        chofer.getNumLicencia(),
                        chofer.getActivo()))
                .collect(Collectors.toSet());

    }

    @Override
    public ResponseChoferDTO getChofer(Long id) {

        Chofer choferBD = choferRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Chofer no encontrado"));

        return new ResponseChoferDTO(
                choferBD.getIdChofer(),
                choferBD.getNombre(),
                choferBD.getApellido(),
                choferBD.getNumLicencia(),
                choferBD.getActivo());
    }

    @Override
    public ResponseChoferDTO createChofer(CreateChoferDTO choferDTO) {
        Chofer chofer = new Chofer(
                null,
                choferDTO.getNombre(),
                choferDTO.getApellido(),
                choferDTO.getNumLicencia(),
                true);
        Chofer guardado = choferRepo.save(chofer);

        return new ResponseChoferDTO(
                guardado.getIdChofer(),
                guardado.getNombre(),
                guardado.getApellido(),
                guardado.getNumLicencia(),
                guardado.getActivo());

    }

    @Override
    public void deleteChofer(Long id) {

        Chofer chofer = choferRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Chofer no encontrado"));

        chofer.setActivo(false);

        choferRepo.save(chofer);
    }

    @Override
    public ResponseChoferDTO editChofer(Long idChofer, CreateChoferDTO choferDTO) {
        Chofer choferBD = choferRepo.findById(idChofer)
                .orElseThrow(() -> new RuntimeException("Chofer no encontrado para editar"));

        choferBD.setNombre(choferDTO.getNombre());
        choferBD.setApellido(choferDTO.getApellido());
        choferBD.setNumLicencia(choferDTO.getNumLicencia());
        choferBD.setActivo(choferDTO.getActivo());

        Chofer guardado = choferRepo.save(choferBD);

        return new ResponseChoferDTO(
                guardado.getIdChofer(),
                guardado.getNombre(),
                guardado.getApellido(),
                guardado.getNumLicencia(),
                guardado.getActivo());
    }

}
