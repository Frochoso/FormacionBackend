package com.example.block17springbatch.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "Tiempo")
public class Tiempo {

    @Id
    @GeneratedValue
    int id;

    String poblacion;

    int temperatura;

    String fecha;

    @OneToOne(mappedBy = "tiempo")
    TiempoRiesgo tiempoRiesgo;

    public Tiempo(String poblacion, int temperatura, String fecha) {
        this.poblacion = poblacion;
        this.temperatura = temperatura;
        this.fecha = fecha;
    }
}
