package com.devsu.op.crm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.op.crm.entity.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, String> {


    List<Movimiento> findByCuentaId(String cuentaId);
    List<Movimiento> findByCuenta_NumeroCuentaAndFechaBetween(String numeroCuenta, Date fechaInicio, Date fechaFin);

}