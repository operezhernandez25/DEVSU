package com.devsu.op.crm.entity;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import com.devsu.op.crm.dto.MovimientoDTO;
import com.devsu.op.crm.exceptions.CuentaNotFoundException;

import jakarta.persistence.*;
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
@Table(name = "movimientos")
public class Movimiento {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false,insertable = false, columnDefinition = "varchar(255)")
    private String id;
    private Date fecha;
    private String tipoMovimiento;
    private Double valor;
 
    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;


   

}