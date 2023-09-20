package com.example.block17springbatch.itemProcessor;

import com.example.block17springbatch.domain.Tiempo;
import com.example.block17springbatch.domain.TiempoRiesgo;
import com.example.block17springbatch.enums.Riesgo;
import org.springframework.batch.item.ItemProcessor;

public class TiempoRiesgoProcessor implements ItemProcessor<Tiempo, TiempoRiesgo> {
    @Override
    public TiempoRiesgo process(Tiempo item) throws Exception {
        TiempoRiesgo tiempoRiesgo = new TiempoRiesgo();

        tiempoRiesgo.setFechaPrediccion(item.getFecha());
        tiempoRiesgo.setTiempo(item);

        if (item.getTemperatura() >= 36) {
            tiempoRiesgo.setRiesgo(Riesgo.ALTO.name());
        } else if (item.getTemperatura() < 36 && item.getTemperatura() >= 32) {
            tiempoRiesgo.setRiesgo(Riesgo.MEDIO.name());
        } else if (item.getTemperatura() < 32) {
            tiempoRiesgo.setRiesgo(Riesgo.BAJO.name());
        }

        return tiempoRiesgo;
    }
}
