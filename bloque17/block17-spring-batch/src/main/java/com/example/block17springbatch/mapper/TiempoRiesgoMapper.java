package com.example.block17springbatch.mapper;

import com.example.block17springbatch.domain.Tiempo;
import com.example.block17springbatch.domain.TiempoRiesgo;
import com.example.block17springbatch.repository.TiempoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TiempoRiesgoMapper  implements RowMapper<TiempoRiesgo> {
    @Autowired
    TiempoRepository tiempoRepository;

    @Override
    public TiempoRiesgo mapRow(ResultSet rs, int rowNum) throws SQLException {
        TiempoRiesgo tiempoRiesgo = new TiempoRiesgo();
        tiempoRiesgo.setId(rs.getInt("id"));
        tiempoRiesgo.setFechaPrediccion(rs.getString("fecha_prediccion"));
        tiempoRiesgo.setRiesgo(rs.getString("riesgo"));

        Tiempo tiempo = tiempoRepository.findById(rs.getInt("id")).orElseThrow();

        tiempoRiesgo.setTiempo(tiempo);

        return tiempoRiesgo;
    }
}
