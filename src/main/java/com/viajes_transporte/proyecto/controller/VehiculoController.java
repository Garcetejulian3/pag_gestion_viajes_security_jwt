package com.viajes_transporte.proyecto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viajes_transporte.proyecto.dto.vehiculoDTO.CreateVehiculoDTO;
import com.viajes_transporte.proyecto.dto.vehiculoDTO.ResponseVehiculoDTO;
import com.viajes_transporte.proyecto.service.inpl.IVehiculoService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/vehiculo")
@CrossOrigin(origins = "*")
public class VehiculoController {

    @Autowired
    private IVehiculoService iVehiculoService;

    @GetMapping
    public List<ResponseVehiculoDTO> finAll() {
        Set<ResponseVehiculoDTO> vehiculoDTO = iVehiculoService.allVehiculo();
        List<ResponseVehiculoDTO> vehiculoList = new ArrayList<>(vehiculoDTO);
        return vehiculoList;
    }

    @GetMapping("{id}")
    public ResponseVehiculoDTO findById(@PathVariable Long id) {
        ResponseVehiculoDTO dto = iVehiculoService.getVehiculo(id);
        return dto;
    }

    @PostMapping("/save")
    public ResponseVehiculoDTO saveVehiculo (@RequestBody CreateVehiculoDTO dto) {
        return iVehiculoService.createVehiculo(dto);
    }
    
    @PutMapping("/edit/{id}")
    public ResponseVehiculoDTO editVehiculo(@PathVariable Long id, @RequestBody CreateVehiculoDTO dto) {
        return iVehiculoService.editVehiculo(id, dto);
    }
    
    @DeleteMapping("/delete/{id}")
    public void eliminarVehiculo(@PathVariable Long id){
        iVehiculoService.deleteVehiculo(id);
    }

}
