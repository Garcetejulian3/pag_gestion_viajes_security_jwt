package com.viajes_transporte.proyecto.service.inpl;

import java.util.List;
import java.util.Optional;

import com.viajes_transporte.proyecto.models.UserSec;

public interface IUserSecService {

    public List findAll();

    public Optional findById(Long id);

    public UserSec save(UserSec userSec);

    public void deleteById(Long id);

    public void update(UserSec userSec);

    public String encriptPassword(String password);

}
