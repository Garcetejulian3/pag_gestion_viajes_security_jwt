package com.viajes_transporte.proyecto.service.inpl;

import java.util.List;
import java.util.Optional;

import com.viajes_transporte.proyecto.models.Role;

public interface IRoleService {

    List findAll();
    Optional findById(Long id);
    Role save(Role role);
    void deleteById(Long id);
    Role update(Role role);
}
