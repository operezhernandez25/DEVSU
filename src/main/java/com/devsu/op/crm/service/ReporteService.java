package com.devsu.op.crm.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.op.crm.dto.CuentaReporteDTO;
import com.devsu.op.crm.dto.MovimientoReporteDTO;
import com.devsu.op.crm.dto.ReporteDTO;
import com.devsu.op.crm.entity.Cuenta;
import com.devsu.op.crm.entity.Movimiento;
import com.devsu.op.crm.exceptions.CuentaNotFoundException;
import com.devsu.op.crm.repository.CuentaRepository;
import com.devsu.op.crm.repository.MovimientoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReporteService {
    

    private final CuentaRepository cuentaRepository;
    
    private final MovimientoRepository movimientoRepository;

    public ReporteDTO generarReporte(String rangoFechas, String clienteId)  {
        String[] fechas = rangoFechas.split(",");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        Date fechaInicio;
        Date fechaFin ;
        try {
            fechaInicio = format.parse(fechas[0]);
            fechaFin = format.parse(fechas[1]);
        } catch (ParseException e) {
            throw new CuentaNotFoundException("Error con parametros");
        }

        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
        
        List<CuentaReporteDTO> listaCuentasDTO = new ArrayList<>();

        for(Cuenta cuenta: cuentas) {
            CuentaReporteDTO cuentaDTO = new CuentaReporteDTO();
            cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
            cuentaDTO.setSaldo(cuenta.getSaldoActual());

            List<Movimiento> movimientos = movimientoRepository.findByCuenta_NumeroCuentaAndFechaBetween(cuenta.getNumeroCuenta(), fechaInicio, fechaFin);
            List<MovimientoReporteDTO> listaMovimientosDTO = new ArrayList<>();

            for(Movimiento movimiento: movimientos) {
                MovimientoReporteDTO movimientoDTO = new MovimientoReporteDTO();
                movimientoDTO.setFecha(movimiento.getFecha());
                movimientoDTO.setTipoMovimiento(movimiento.getTipoMovimiento());
                movimientoDTO.setValor(movimiento.getValor());

                listaMovimientosDTO.add(movimientoDTO);
            }

            cuentaDTO.setMovimientos(listaMovimientosDTO);
            listaCuentasDTO.add(cuentaDTO);
        }

        ReporteDTO reporte = new ReporteDTO();
        reporte.setCuentas(listaCuentasDTO);
        
        return reporte;
    }
    
}
