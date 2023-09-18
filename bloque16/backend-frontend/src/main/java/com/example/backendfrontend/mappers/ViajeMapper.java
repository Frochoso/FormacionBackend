package com.example.backendfrontend.mappers;

import com.example.backendfrontend.domain.Viaje;
import com.example.backendfrontend.dto.viaje.ViajeDtoGet;
import com.example.backendfrontend.dto.viaje.ViajeDtoPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ViajeMapper {

    @Mapping(target = "idViaje", source = "idViaje")
    @Mapping(target = "passenger", source = "passenger")
    ViajeDtoGet viajeToViajeDtoGet(Viaje viaje);

    Viaje viajeDtoPostToViaje(ViajeDtoPost viajeDtoPost);

    @Mapping(target = "idViaje", source = "idViaje")
    @Mapping(target = "passenger", source = "passenger")
    Viaje viajeDtoGetToViaje(ViajeDtoGet viajeDtoGet);

}
