package com.viajes_transporte.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.viajes_transporte.proyecto.models.UserSec;
import com.viajes_transporte.proyecto.repository.IUserSecRepository;
import com.viajes_transporte.proyecto.service.inpl.IUserSecService;

@Service
public class UserSecService implements IUserSecService {

    @Autowired
    private IUserSecRepository userSecRepository;

    @Override
    public List findAll() {
        return userSecRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return userSecRepository.findById(id);
    }

    @Override
    public UserSec save(UserSec userSec) {
        return userSecRepository.save(userSec);
    }

    @Override
    public void deleteById(Long id) {
        userSecRepository.deleteById(id);
    }

    @Override
    public void update(UserSec userSec) {
        userSecRepository.save(userSec);
    }

    @Override
    public String encriptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}