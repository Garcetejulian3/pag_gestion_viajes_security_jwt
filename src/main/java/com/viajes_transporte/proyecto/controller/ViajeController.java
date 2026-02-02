package com.viajes_transporte.proyecto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viajes_transporte.proyecto.dto.viajesDTO.CreateViaje;
import com.viajes_transporte.proyecto.dto.viajesDTO.ResponseViaje;
import com.viajes_transporte.proyecto.service.inpl.IViajeService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/viaje")
public class ViajeController {

    @Autowired
    private IViajeService iViajeService;

    @GetMapping
    public List<ResponseViaje> allViaje() {
        Set<ResponseViaje> viajeDTO = iViajeService.allViaje();
        List<ResponseViaje> viajeList = new ArrayList<>(viajeDTO);
        return viajeList;
    }
    
    @GetMapping("/{id}")
    public ResponseViaje findVijae(@PathVariable Long id) {
        ResponseViaje dto = iViajeService.getViaje(id);
        return dto;
    }

    @PostMapping("/save")
    public ResponseViaje saveViaje(@RequestBody CreateViaje dto) {
        return iViajeService.createViaje(dto);
    }
    
    @PutMapping("/edit/{id}")
    public ResponseViaje editViaje(@PathVariable Long id, @RequestBody CreateViaje edtotity) {
        return iViajeService.editViaje(id, edtotity);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteViaje(@PathVariable Long id){
        iViajeService.deleteViaje(id);
    }
}
