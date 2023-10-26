package com.devsu.op.crm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsu.op.crm.dto.MovimientoDTO;
import com.devsu.op.crm.entity.Movimiento;
import com.devsu.op.crm.exceptions.SaldoInsuficienteException;
import com.devsu.op.crm.repository.MovimientoRepository;
import com.devsu.op.crm.service.CuentaService;
import com.devsu.op.crm.service.MovimientoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoService movimientoService;

    @GetMapping
    public ResponseEntity<List<Movimiento>> getAllMovimientos() {
        return ResponseEntity.ok(movimientoService.getAllMovimientos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getMovimientoById(@PathVariable String id) {
        Movimiento movimiento = movimientoService.getMovimientoById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
        return ResponseEntity.ok(movimiento);
    }

    @PostMapping
    public ResponseEntity<Movimiento> createMovimiento(@Valid @RequestBody MovimientoDTO movimiento) {
            Movimiento nuevoMovimiento = movimientoService.createMovimiento(movimiento);
            return ResponseEntity.ok(nuevoMovimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovimiento(@PathVariable String id) {
    
        return ResponseEntity.badRequest().body("Los movimientos no pueden ser eliminados por tema de auditoria");
    }

}