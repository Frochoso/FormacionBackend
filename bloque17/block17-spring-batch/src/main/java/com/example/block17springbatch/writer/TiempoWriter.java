package com.example.block17springbatch.writer;

import com.example.block17springbatch.domain.Tiempo;
import com.example.block17springbatch.error.ErrorCounterComponent;
import com.example.block17springbatch.repository.TiempoRepository;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class TiempoWriter implements ItemWriter<Tiempo> {


    @Autowired
    ErrorCounterComponent errorCounterComponent;
    @Autowired
    TiempoRepository tiempoRepository;

    private boolean isJobFailed = false;
    private List<Tiempo> erroresChunk = new ArrayList<>();
    private int erroresCounter = 0;

    public TiempoWriter(TiempoRepository tiempoRepository, ErrorCounterComponent errorCounterComponent) {
        this.tiempoRepository = tiempoRepository;
        this.errorCounterComponent = errorCounterComponent;
    }

    @Override
    public void write(List<? extends Tiempo> chunk) throws Exception {
        if (!isJobFailed) {
            List<Tiempo> registrosErrores = new ArrayList<>();

            chunk.forEach(tiempo -> {
                if (tiempo.getTemperatura() >= 50 || tiempo.getTemperatura() <= -20) {
                    registrosErrores.add(tiempo);
                    erroresChunk.add(tiempo);
                    errorCounterComponent.getErrorCounter().increment();
                    erroresCounter++;

                } else {
                    erroresChunk.clear();
                    tiempoRepository.save(tiempo);
                    erroresCounter = 0;
                }

                if (erroresCounter == 5) {
                    try {
                        escribirChunkErrores(erroresChunk);
                        erroresChunk.clear();
                        erroresCounter = 0;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            if (errorCounterComponent.getErrorCounter().getCount() >= 100) {
                tiempoRepository.deleteAll();
                isJobFailed = true;
            }

            if (!registrosErrores.isEmpty()) {
                escribirErroneos(registrosErrores);
            }
        }
    }

    void escribirErroneos(List<Tiempo> registrosErrores) throws IOException {
        String newFile = "mal.csv";
        BufferedWriter bf = new BufferedWriter(new FileWriter(newFile, true));

        try {
            for (Tiempo registro : registrosErrores) {

                String line = String.format("%s,%s,%s",
                        registro.getPoblacion(),
                        registro.getTemperatura(),
                        registro.getFecha());
                bf.write(line);
                bf.newLine();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            bf.close();
        }
    }

    void escribirChunkErrores(List<Tiempo> registrosErrores) throws IOException {
        String newFile = "CHUNKS_ERRONEOS.csv";
        BufferedWriter bf = new BufferedWriter(new FileWriter(newFile, true));
        try {
            for (Tiempo registro : registrosErrores) {

                String line = String.format("%s,%s,%s",
                        registro.getPoblacion(),
                        registro.getTemperatura(),
                        registro.getFecha());
                bf.write(line);
                bf.newLine();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            bf.close();
        }
    }
}