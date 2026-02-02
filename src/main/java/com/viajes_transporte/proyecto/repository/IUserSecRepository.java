package com.viajes_transporte.proyecto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viajes_transporte.proyecto.models.UserSec;


@Repository
public interface IUserSecRepository extends JpaRepository<UserSec,Long>{
    Optional<UserSec> findByUsername(String username);
}
