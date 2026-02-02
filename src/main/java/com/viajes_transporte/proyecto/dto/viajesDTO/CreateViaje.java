package com.viajes_transporte.proyecto.dto.viajesDTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CreateViaje {

    private String destino;
    private LocalDateTime fechaSalida;
    private Long idChofer;
    private Long idVehiculo;

}
