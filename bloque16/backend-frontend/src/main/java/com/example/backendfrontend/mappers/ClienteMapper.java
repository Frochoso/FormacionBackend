package com.example.backendfrontend.mappers;

import com.example.backendfrontend.domain.Cliente;
import com.example.backendfrontend.dto.cliente.ClienteDtoGet;
import com.example.backendfrontend.dto.cliente.ClienteDtoPost;
import org.mapstruct.Mapper;

@Mapper
public interface ClienteMapper {

    ClienteDtoGet clienteToClienteDtoGet(Cliente cliente);

    Cliente clienteDtoPostToCliente(ClienteDtoPost clienteDtoPost);

    Cliente clienteDtoGetToCliente(ClienteDtoGet clienteDtoGet);

}
