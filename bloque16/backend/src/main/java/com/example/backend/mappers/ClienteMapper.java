package com.example.backend.mappers;

import com.example.backend.domain.Cliente;
import com.example.backend.dto.cliente.ClienteDtoGet;
import com.example.backend.dto.cliente.ClienteDtoPost;
import org.mapstruct.Mapper;

@Mapper
public interface ClienteMapper {

    ClienteDtoGet clienteToClienteDtoGet(Cliente cliente);

    Cliente clienteDtoPostToCliente(ClienteDtoPost clienteDtoPost);

    Cliente clienteDtoGetToCliente(ClienteDtoGet clienteDtoGet);

}
