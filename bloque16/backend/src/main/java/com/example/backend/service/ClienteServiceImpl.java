package com.example.backend.service;

import com.example.backend.domain.Cliente;
import com.example.backend.dto.cliente.ClienteDtoGet;
import com.example.backend.dto.cliente.ClienteDtoPost;
import com.example.backend.mappers.ClienteMapper;
import com.example.backend.repository.ClienteRepository;
import com.example.backend.service.interfaces.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    ClienteMapper clienteMapper = Mappers.getMapper(ClienteMapper.class);


    @Override
    public ClienteDtoGet addCliente(ClienteDtoPost clienteDtoPost) {
        return clienteMapper.clienteToClienteDtoGet(clienteRepository
                .save(clienteMapper.clienteDtoPostToCliente(clienteDtoPost)));
    }

    @Override
    public ClienteDtoGet removeCliente(int idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(()
                -> {
            return new EntityNotFoundException("Cliente con id: " + idCliente + " no encontrado.");
        });
        clienteRepository.delete(cliente);
        return clienteMapper.clienteToClienteDtoGet(cliente);
    }

    @Override
    public ClienteDtoGet updateCliente(int idCliente, ClienteDtoPost clienteDtoPost) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(()
                -> new EntityNotFoundException("Cliente con id: " + idCliente + " no encontrado."));

        cliente.setApellido(clienteDtoPost.getApellido());
        cliente.setTfno(clienteDtoPost.getTfno());
        cliente.setEmail(clienteDtoPost.getEmail());
        cliente.setEdad(clienteDtoPost.getEdad());
        cliente.setNombre(clienteDtoPost.getNombre());

        return clienteMapper.clienteToClienteDtoGet(clienteRepository.save(cliente));
    }

    @Override
    public ClienteDtoGet findCliente(int idCliente) {
        return clienteMapper.clienteToClienteDtoGet(clienteRepository.findById(idCliente).orElseThrow(() ->
                new EntityNotFoundException("Cliente con id: " + idCliente + " no encontrado.")));
    }

    @Override
    public int countPassengers(int idViaje) {
        return clienteRepository.passengerCount(idViaje);
    }
}
