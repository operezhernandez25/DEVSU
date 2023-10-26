package com.devsu.op.crm.service;

import com.devsu.op.crm.dto.CuentaDTO;
import com.devsu.op.crm.entity.Cliente;
import com.devsu.op.crm.entity.Cuenta;
import com.devsu.op.crm.entity.Movimiento;
import com.devsu.op.crm.exceptions.ClienteNotFoundException;
import com.devsu.op.crm.exceptions.CuentaNotFoundException;
import com.devsu.op.crm.exceptions.NumeroCuentaExistenteException;
import com.devsu.op.crm.exceptions.SaldoInsuficienteException;
import com.devsu.op.crm.repository.CuentaRepository;
import com.devsu.op.crm.repository.MovimientoRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteService clienteService;
    private final MovimientoRepository movimientoRepository;

    public Cuenta insertarCuenta(Cuenta cuenta) {
        Cuenta cuentaExistente = cuentaRepository.findByNumeroCuenta(cuenta.getNumeroCuenta()).orElse(null);
        if (cuentaExistente != null) {
            throw new NumeroCuentaExistenteException("El número de cuenta ya existe.");
        }
        return cuentaRepository.save(cuenta);
    }

    public List<Cuenta> obtenerTodasLasCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta obtenerCuentaPorId(String id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new CuentaNotFoundException("Cuenta no encontrada con el ID: " + id));
    }

    public Cuenta obtenerCuentaPorNumeroDeCuenta(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new CuentaNotFoundException("Cuenta no encontrada con el numero de cuenta: " + numeroCuenta));
    }

    public List<Cuenta> obtenerCuentasPorClienteId(String clienteId) {
        Cliente cliente = clienteService.obtenerPorId(clienteId);
        if (cliente == null) {
            throw new ClienteNotFoundException("Cliente no encontrado con el ID proporcionado.");
        }
        return cuentaRepository.findByClienteId(clienteId);
    }


    public Movimiento registrarMovimiento(Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getId()).orElseThrow(() -> new CuentaNotFoundException("Cuenta no encontrada con el numero de cuenta: " + movimiento.getCuenta().getId()));
        
        if(movimiento.getValor() < 0 && Math.abs(movimiento.getValor()) > cuenta.getSaldoActual()) {
            throw new SaldoInsuficienteException("Saldo no disponible");
        }

        cuenta.setSaldoActual(cuenta.getSaldoActual() + movimiento.getValor());
        cuentaRepository.save(cuenta);
        movimientoRepository.save(movimiento);

        return movimiento;
    }

    public Cuenta convertirDtoAEntidad(CuentaDTO cuentaDTO) {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDTO.getSaldoInicial());
        cuenta.setSaldoActual(cuentaDTO.getSaldoInicial());
        cuenta.setEstado(cuentaDTO.getEstado());

        Cliente cliente = clienteService.obtenerPorId(cuentaDTO.getClienteId());
        if (cliente == null) {
            throw new ClienteNotFoundException("No se encontró el cliente con ID: " + cuentaDTO.getClienteId());
        }
        cuenta.setCliente(cliente);

        return cuenta;
    }


}