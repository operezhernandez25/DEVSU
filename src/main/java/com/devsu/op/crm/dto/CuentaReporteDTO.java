package com.devsu.op.crm.dto;

import java.util.List;

import lombok.Data;

@Data
public class CuentaReporteDTO {
    private String numeroCuenta;
    private Double saldo;
    private List<MovimientoReporteDTO> movimientos;

}