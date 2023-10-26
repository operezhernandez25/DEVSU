package com.devsu.op.crm.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Persona {

    @Column(unique = true)
    @NotBlank(message = "El clienteId no puede estar vacío.")
    @Size(min = 1, max = 255, message = "El clienteId debe tener entre 1 y 255 caracteres.")
    private String clienteId;

    @NotBlank(message = "La contraseña no puede estar vacía.")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres.")
    private String contraseña;

    @NotBlank(message = "El estado no puede estar vacío.")
    private String estado;

    // @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private List<Cuenta> cuentas;

}