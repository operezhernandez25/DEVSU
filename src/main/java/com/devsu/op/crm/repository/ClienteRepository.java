package com.devsu.op.crm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.op.crm.entity.Cliente;
import com.devsu.op.crm.entity.Persona;

public interface ClienteRepository extends JpaRepository<Cliente, String> {


    Optional<Cliente> findByIdentificacion(String identificacion);
    Optional<Cliente> findByClienteId(String clienteId);
}