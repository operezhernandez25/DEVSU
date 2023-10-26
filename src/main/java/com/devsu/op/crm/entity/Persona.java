package com.devsu.op.crm.entity;


import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
public class Persona {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false,insertable = false, columnDefinition = "varchar(255)")
    private String id;
    @NotNull(message = "El campo nombre no puede ser nulo")
    @Size(min = 1, message = "El nombre no puede estar vacío")
    private String nombre;

    @NotNull(message = "El campo género no puede ser nulo")
    @Size(min = 1, message = "El género no puede estar vacío")
    private String genero;

    @NotNull(message = "El campo edad no puede ser nulo")
    @Min(value = 0, message = "La edad no puede ser negativa")
    private int edad;

    @NotNull(message = "El campo identificación no puede ser nulo")
    @Size(min = 1, message = "La identificación no puede estar vacía")
    private String identificacion;

    @NotNull(message = "El campo dirección no puede ser nulo")
    @Size(min = 1, message = "La dirección no puede estar vacía")
    private String direccion;

    @NotNull(message = "El campo teléfono no puede ser nulo")
    @Size(min = 1, message = "El teléfono no puede estar vacío")
    private String telefono;

    @PrePersist
    public void generateUUID() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }

}