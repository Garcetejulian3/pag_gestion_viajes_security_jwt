package com.viajes_transporte.proyecto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viajes_transporte.proyecto.dto.choferDTO.CreateChoferDTO;
import com.viajes_transporte.proyecto.dto.choferDTO.ResponseChoferDTO;
import com.viajes_transporte.proyecto.service.inpl.IChoferService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/chofer")
@CrossOrigin(origins = "*")
public class ChoferController {

    @Autowired
    private IChoferService choferService;

    @GetMapping
    public List<ResponseChoferDTO> allChoferes() {
        Set<ResponseChoferDTO> choferesDTO = choferService.allChofer();
        List<ResponseChoferDTO> listChoferes = new ArrayList<>(choferesDTO);
        return listChoferes;
    }

    @GetMapping("{id}")
    public ResponseChoferDTO getChofer(@PathVariable Long id) {
        ResponseChoferDTO dto = choferService.getChofer(id);
        return dto;
    }        

    @PostMapping("/save")
    public ResponseChoferDTO saveChoferes(@RequestBody CreateChoferDTO dto) {
        return choferService.createChofer(dto);
    }
    
    // FORM EDITAR
    @PutMapping("/editar/{id}")
    public ResponseChoferDTO editChofer(@PathVariable Long id,@RequestBody CreateChoferDTO dto ) {
        return choferService.editChofer(id, dto);    
    }

    // ELIMINAR
    @DeleteMapping("/eliminar/{id}")
    public void eliminarChofer(@PathVariable Long id) {
        choferService.deleteChofer(id);
    }



}
