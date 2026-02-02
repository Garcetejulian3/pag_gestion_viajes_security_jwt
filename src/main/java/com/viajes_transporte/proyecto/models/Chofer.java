package com.viajes_transporte.proyecto.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Chofer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChofer;
    private String nombre;
    private String apellido;
    private String numLicencia;
    private Boolean activo = true;

}
