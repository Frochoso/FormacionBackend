package com.example.block17springbatch.writer;

import com.example.block17springbatch.domain.Tiempo;
import com.example.block17springbatch.domain.TiempoRiesgo;
import com.example.block17springbatch.repository.TiempoRepository;
import com.example.block17springbatch.repository.TiempoRiesgoRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TiempoRiesgoWriter implements ItemWriter<TiempoRiesgo> {
    @Autowired
    TiempoRiesgoRepository tiempoRiesgoRepository;
    @Autowired
    TiempoRepository tiempoRepository;

    @Override
    public void write(List<? extends TiempoRiesgo> list) throws Exception {
        list.forEach(tiempoRiesgo -> {
            Tiempo tiempo = tiempoRepository
                    .findById(tiempoRiesgo.getTiempo().getId())
                    .orElseThrow(() -> new RuntimeException("Tiempo con id: " + tiempoRiesgo.getTiempo().getId() + " no existe."));

            tiempo.setTiempoRiesgo(tiempoRiesgo);

            tiempoRepository.save(tiempo);
            tiempoRiesgoRepository.save(tiempoRiesgo);
        });
    }
}
