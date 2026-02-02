package com.viajes_transporte.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viajes_transporte.proyecto.models.Role;
import com.viajes_transporte.proyecto.repository.IRoleRepository;
import com.viajes_transporte.proyecto.service.inpl.IRoleService;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository iRoleRepository;

    @Override
    public List findAll() {
        return iRoleRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return iRoleRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return iRoleRepository.save(role);
    }

    @Override
    public void deleteById(Long id) {
        iRoleRepository.deleteById(id);
    }

    @Override
    public Role update(Role role) {
        return iRoleRepository.save(role);
    }
}