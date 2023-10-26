package com.devsu.op.crm.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.op.crm.entity.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, String> {

    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
    List<Cuenta> findByClienteId(String clienteId);
    List<Cuenta> findAllByNumeroCuenta(String numeroCuenta);
}