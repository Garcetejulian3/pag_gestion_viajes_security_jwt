package com.viajes_transporte.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viajes_transporte.proyecto.models.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo,Long>{

}
