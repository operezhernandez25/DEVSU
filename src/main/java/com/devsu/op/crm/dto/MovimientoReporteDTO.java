package com.devsu.op.crm.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MovimientoReporteDTO {
    
    private Date fecha;
    private String tipoMovimiento;
    private Double valor;
}
