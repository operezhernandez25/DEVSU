package com.devsu.op.crm.entity;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cuentas")
public class Cuenta {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false,insertable = false, columnDefinition = "varchar(255)")
    private String id;
    
    @NotBlank(message = "El número de cuenta no puede estar vacío.")
    @Size(min = 10, max = 10, message = "El número de cuenta debe tener 10 caracteres")
    private String numeroCuenta;

    @NotBlank(message = "El tipo de cuenta no puede estar vacío.")
    @Size(min = 1, max = 50, message = "El tipo de cuenta debe tener entre 1 y 50 caracteres.")
    private String tipoCuenta;

    @NotNull(message = "El saldo inicial no puede ser nulo.")
    @DecimalMin(value = "0.0", message = "El saldo inicial no puede ser negativo.")
    private Double saldoInicial;

    @NotBlank(message = "El estado no puede estar vacío.")
    @Size(min = 1, max = 50, message = "El estado debe tener entre 1 y 50 caracteres.")
    private String estado;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    @NotNull(message = "El cliente no puede ser nulo.")
    private Cliente cliente;
    private Double saldoActual;

}