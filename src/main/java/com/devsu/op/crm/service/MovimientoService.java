package com.devsu.op.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.op.crm.dto.MovimientoDTO;
import com.devsu.op.crm.entity.Cuenta;
import com.devsu.op.crm.entity.Movimiento;
import com.devsu.op.crm.exceptions.CuentaNotFoundException;
import com.devsu.op.crm.repository.MovimientoRepository;

import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaService cuentaService;

    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    public Optional<Movimiento> getMovimientoById(String id) {
        return movimientoRepository.findById(id);
    }

    public Movimiento createMovimiento(MovimientoDTO movimiento) {
        return cuentaService.registrarMovimiento(createMovimientoFromDTO(movimiento));
    }

    public void deleteMovimiento(String id) {
        movimientoRepository.deleteById(id);
    }

     public Movimiento createMovimientoFromDTO(MovimientoDTO movimientoDTO) {
        Cuenta cuenta = cuentaService.obtenerCuentaPorNumeroDeCuenta(movimientoDTO.getNumeroCuenta());
        if (cuenta == null) {
            throw new CuentaNotFoundException("No se encontró la cuenta con número: " + movimientoDTO.getNumeroCuenta());
        }
        
        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setValor(movimientoDTO.getValor());
        movimiento.setTipoMovimiento(movimientoDTO.getTipoMovimiento());
        movimiento.setFecha(Timestamp.valueOf(movimientoDTO.getFecha()));
        
        return (movimiento);
    }

}