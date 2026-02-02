package com.viajes_transporte.proyecto.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idViajes;
    private String destino;

    private LocalDateTime fechaSalida;


    @ManyToOne
    @JoinColumn(name = "chofer_id",nullable = false)
    private Chofer chofer;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id",nullable = false)
    private Vehiculo vehiculo;
}
