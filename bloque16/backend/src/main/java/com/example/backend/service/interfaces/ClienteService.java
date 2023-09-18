package com.example.backend.service.interfaces;

import com.example.backend.dto.cliente.ClienteDtoGet;
import com.example.backend.dto.cliente.ClienteDtoPost;
import org.springframework.stereotype.Service;

@Service
public interface ClienteService {

    ClienteDtoGet addCliente(ClienteDtoPost clienteDtoPost);

    ClienteDtoGet removeCliente(int idCliente);

    ClienteDtoGet updateCliente(int idCliente, ClienteDtoPost clienteDtoPost);

    ClienteDtoGet findCliente(int idCliente);

    int countPassengers(int idViaje);

}
