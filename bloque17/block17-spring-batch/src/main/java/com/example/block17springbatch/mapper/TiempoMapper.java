package com.example.block17springbatch.mapper;

import com.example.block17springbatch.domain.Tiempo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TiempoMapper implements RowMapper<Tiempo> {

    @Override
    public Tiempo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tiempo tiempo = new Tiempo();
        tiempo.setId(rs.getInt("id"));
        tiempo.setPoblacion(rs.getString("poblacion"));
        tiempo.setTemperatura(rs.getInt("temperatura"));
        tiempo.setFecha(rs.getString("fecha"));

        return tiempo;
    }
}
