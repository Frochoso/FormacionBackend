package com.example.block15listener.kafka;

import com.example.block15listener.kafka.json.Coche;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mensaje
{
    Coche coche;
    String mensaje;
    Date fecha;
    int particion;
    String topico;
    boolean recibido=false;
}
