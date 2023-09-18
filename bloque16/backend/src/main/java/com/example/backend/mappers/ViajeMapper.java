package com.example.backend.mappers;

import com.example.backend.domain.Viaje;
import com.example.backend.dto.viaje.ViajeDtoGet;
import com.example.backend.dto.viaje.ViajeDtoPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ViajeMapper {

    @Mapping(target = "idViaje", source = "idViaje")
    @Mapping(target = "passenger", source = "passenger")
    ViajeDtoGet viajeToViajeDtoGet(Viaje viaje);

    Viaje viajeDtoPostToViaje(ViajeDtoPost viajeDtoPost);

}
