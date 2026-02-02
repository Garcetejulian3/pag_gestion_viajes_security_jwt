package com.viajes_transporte.proyecto.dto.vehiculoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CreateVehiculoDTO {

    private String modelo;
    private String codigoIdent;
    private Boolean estado;
}
