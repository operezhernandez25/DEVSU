package com.devsu.op.crm.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.op.crm.dto.CuentaDTO;
import com.devsu.op.crm.entity.Cuenta;
import com.devsu.op.crm.service.CuentaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService cuentaService;  

    @PostMapping()
    public ResponseEntity<Cuenta> crearCuenta(@Valid @RequestBody CuentaDTO cuentaDTO) {
        Cuenta cuenta = cuentaService.convertirDtoAEntidad(cuentaDTO);
        Cuenta nuevaCuenta = cuentaService.insertarCuenta(cuenta);
        return new ResponseEntity<>(nuevaCuenta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cuenta>> obtenerTodasLasCuentas() {
        List<Cuenta> cuentas = cuentaService.obtenerTodasLasCuentas();
        return ResponseEntity.ok(cuentas);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtenerCuentaPorId(@PathVariable String id) {
        Cuenta cuenta = cuentaService.obtenerCuentaPorId(id);
        return ResponseEntity.ok(cuenta);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Cuenta>> obtenerCuentasPorClienteId(@PathVariable String clienteId) {
        List<Cuenta> cuentas = cuentaService.obtenerCuentasPorClienteId(clienteId);
        return ResponseEntity.ok(cuentas);
    }
        
}

