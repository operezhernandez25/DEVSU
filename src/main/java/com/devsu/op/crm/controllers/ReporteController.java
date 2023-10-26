package com.devsu.op.crm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsu.op.crm.dto.ReporteDTO;
import com.devsu.op.crm.service.ReporteService;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public ResponseEntity<ReporteDTO> generarReporte(@RequestParam("fecha") String rangoFechas, @RequestParam("cliente") String clienteId) {
        ReporteDTO reporte = reporteService.generarReporte(rangoFechas, clienteId);
        if(reporte == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reporte);
    }
}
