package com.viajes_transporte.proyecto.dto.choferDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ResponseChoferDTO {

    private Long idChofer;
    private String nombre;
    private String apellido;
    private String numLicencia;
    private Boolean activo;

}
