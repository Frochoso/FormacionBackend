package com.example.backendfrontend.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDtoGet {

    private int Id;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private int tfno;


}
