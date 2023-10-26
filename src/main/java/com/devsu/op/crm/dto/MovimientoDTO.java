package com.devsu.op.crm.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoDTO {
     @NotNull(message = "El número de cuenta es obligatorio.")
    @Size(min = 5, max = 20, message = "El número de cuenta debe tener entre 5 y 20 caracteres.")
    private String numeroCuenta;

    private double valor; 

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime fecha;


    @NotNull(message = "El tipo de movimiento es obligatorio.")
    @Size(max = 50, message = "El tipo de movimiento no puede exceder los 50 caracteres.")
    private String tipoMovimiento;
}
