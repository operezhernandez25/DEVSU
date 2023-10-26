package com.devsu.op.crm.service;

import org.springframework.stereotype.Service;

import com.devsu.op.crm.entity.Cliente;
import com.devsu.op.crm.entity.Persona;
import com.devsu.op.crm.exceptions.ClienteNotFoundException;
import com.devsu.op.crm.exceptions.IdentificacionExistenteException;
import com.devsu.op.crm.exceptions.IdentificacionNoEncontradaException;
import com.devsu.op.crm.repository.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {
    
    private final ClienteRepository clienteRepository;

 
    public Cliente crearClienteConIdentificacion(Cliente cliente) {
        Cliente existente = obtenerPorIdentificacion(cliente.getIdentificacion());
        if(existente != null) {
            throw new IdentificacionExistenteException("La identificación ya existe en la base de datos.");
        }
        existente = obtenerPorClienteId(cliente.getClienteId());
        if(existente != null) {
            throw new IdentificacionExistenteException("El clienteId ya existe en la base de datos.");
        }
        return clienteRepository.save(cliente);
    }

    public Cliente getClienteById(String clienteId) {
        return clienteRepository.findByClienteId(clienteId).orElseThrow(() -> new ClienteNotFoundException("Cliente con ID " + clienteId + " no encontrado"));
    }

    public Cliente obtenerPorIdentificacion(String identificacion) {
        return clienteRepository.findByIdentificacion(identificacion)
                .orElse(null);
    }

    public Cliente obtenerPorClienteId(String clienteId) {
        return clienteRepository.findByClienteId(clienteId)
                .orElse(null);
    }

    public Cliente obtenerPorId(String id) {
        return clienteRepository.findById(id)
                .orElse(null);
    }

    public Cliente updateCliente(String id, Cliente clienteActualizado) {
        Cliente clienteExistente = clienteRepository.findById(id)
            .orElseThrow(() -> new ClienteNotFoundException("Cliente con ID [" + id + "] no encontrado"));

        clienteExistente.setClienteId(clienteActualizado.getClienteId());
        clienteExistente.setContraseña(clienteActualizado.getContraseña());
        clienteExistente.setEstado(clienteActualizado.getEstado());
        clienteExistente.setDireccion(clienteActualizado.getDireccion());
        clienteExistente.setEdad(clienteActualizado.getEdad());
        clienteExistente.setGenero(clienteActualizado.getGenero());
        clienteExistente.setNombre(clienteActualizado.getNombre());
        clienteExistente.setTelefono(clienteActualizado.getTelefono());

        return clienteRepository.save(clienteExistente);
    }

    public void eliminarCliente(String id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            throw new ClienteNotFoundException("No se encontró el cliente con el ID [" + id+"]");
        }
    }

}


