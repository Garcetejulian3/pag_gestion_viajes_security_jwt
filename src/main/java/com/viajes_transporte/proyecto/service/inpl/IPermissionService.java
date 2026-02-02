package com.viajes_transporte.proyecto.service.inpl;

import java.util.List;
import java.util.Optional;

import com.viajes_transporte.proyecto.models.Permission;

public interface IPermissionService {
    
    List findAll();
    Optional findById(Long id);
    Permission save(Permission permission);
    void deleteById(Long id);
    Permission update(Permission permission);
}
