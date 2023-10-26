package com.devsu.op.crm.dto;

import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CuentaDTO {


    @NotBlank(message = "El número de cuenta no puede estar vacío.")
    private String numeroCuenta;

    @NotBlank(message = "El tipo de cuenta no puede estar vacío.")
    @Size(min = 1, max = 50, message = "El tipo de cuenta debe tener entre 1 y 50 caracteres.")
    private String tipoCuenta;

    @NotNull(message = "El saldo inicial no puede ser nulo.")
    @DecimalMin(value = "0.0", message = "El saldo inicial no puede ser negativo.")
    private Double saldoInicial;

    @NotBlank(message = "El estado no puede estar vacío.")
    @Size(min = 1, max = 50, message = "El estado debe tener entre 1 y 50 caracteres.")
    private String estado;

    @JoinColumn(name = "cliente_id")
    @NotNull(message = "El cliente no puede ser nulo.")
    private String clienteId; 

}