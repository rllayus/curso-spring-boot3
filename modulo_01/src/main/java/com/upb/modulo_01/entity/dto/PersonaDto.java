package com.upb.modulo_01.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class PersonaDto {
    private String nombre;
    private String apellido;
    private short edad;
    @JsonProperty(value = "fecha_nacimiento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    private Boolean pagado = false;

    private ContactoDto contacto;

    List<DireccionDto> direcciones;

}
