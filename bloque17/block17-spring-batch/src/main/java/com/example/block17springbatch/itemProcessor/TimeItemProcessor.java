package com.example.block17springbatch.itemProcessor;

import com.example.block17springbatch.domain.Tiempo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class TimeItemProcessor implements ItemProcessor<Tiempo, Tiempo> {

    private static final Logger log = LoggerFactory.getLogger((TimeItemProcessor.class));

    @Override
    public Tiempo process(Tiempo item) throws Exception {

        Tiempo tiempo = new Tiempo();
        tiempo.setPoblacion(item.getPoblacion());
        tiempo.setTemperatura(item.getTemperatura());
        tiempo.setFecha(item.getFecha());

        log.info("TIEMPO:");

        return tiempo;
    }
}
