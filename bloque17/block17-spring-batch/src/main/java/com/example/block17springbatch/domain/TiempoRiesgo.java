package com.example.block17springbatch.domain;

import com.example.block17springbatch.enums.Riesgo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
public class TiempoRiesgo {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "id")
    Tiempo tiempo;

    String fechaPrediccion;

    String riesgo;

}
