package com.viajes_transporte.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viajes_transporte.proyecto.models.Permission;
import com.viajes_transporte.proyecto.repository.IPermissionRepository;
import com.viajes_transporte.proyecto.service.inpl.IPermissionService;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionRepository iPermissionRepo;

    @Override
    public List findAll() {
        return iPermissionRepo.findAll();
        
    }

    @Override
    public Optional findById(Long id) {
        return iPermissionRepo.findById(id);
    }

    @Override
    public Permission save(Permission permission) {
        return iPermissionRepo.save(permission);
    }

    @Override
    public void deleteById(Long id) {
        iPermissionRepo.deleteById(id);
    }

    @Override
    public Permission update(Permission permission) {
        return iPermissionRepo.save(permission);
    }

    
 
}