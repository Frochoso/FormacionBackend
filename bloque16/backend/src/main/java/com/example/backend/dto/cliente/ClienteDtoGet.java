package com.example.backend.dto.cliente;

import lombok.*;

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
