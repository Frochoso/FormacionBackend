package com.example.backendfrontend.dto.cliente;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteDtoPost {

    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private int tfno;


}
