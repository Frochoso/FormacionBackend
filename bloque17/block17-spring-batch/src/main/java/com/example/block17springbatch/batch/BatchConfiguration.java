package com.example.block17springbatch.batch;

import com.example.block17springbatch.domain.Tiempo;
import com.example.block17springbatch.domain.TiempoRiesgo;
import com.example.block17springbatch.error.ErrorCounterComponent;
import com.example.block17springbatch.itemProcessor.TiempoRiesgoProcessor;
import com.example.block17springbatch.itemProcessor.TimeItemProcessor;
import com.example.block17springbatch.mapper.TiempoMapper;
import com.example.block17springbatch.repository.TiempoRepository;
import com.example.block17springbatch.writer.TiempoRiesgoWriter;
import com.example.block17springbatch.writer.TiempoWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    DataSource dataSource;

    @Bean
    public ItemProcessor<Tiempo, Tiempo> tiempoItemProcessor() {
        return new TimeItemProcessor();
    }

    @Bean
    public ItemProcessor<Tiempo, TiempoRiesgo> tiempoRiesgoItemProcessor() {
        return new TiempoRiesgoProcessor();
    }

    @Bean
    public ItemWriter<Tiempo> tiempoItemWriter(ErrorCounterComponent errorCounterComponent, TiempoRepository tiempoRepository) {
        return new TiempoWriter(tiempoRepository, errorCounterComponent);
    }

    @Bean
    public ItemWriter<TiempoRiesgo> tiempoRiesgoItemWriter() {
        return new TiempoRiesgoWriter();
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobListener();
    }

    @Bean
    public FlatFileItemReader<Tiempo> reader() {
        return new FlatFileItemReaderBuilder<Tiempo>()
                .name("reader")
                .resource(new ClassPathResource("bien.csv"))
                .linesToSkip(1)
                .delimited()
                .names("poblacion", "temperatura", "fecha")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Tiempo>() {{
                    setTargetType(Tiempo.class);

                }})
                .build();
    }

    @Bean
    public JdbcCursorItemReader<Tiempo> tiempoReaderDataBase() {

        JdbcCursorItemReader<Tiempo> reader = new JdbcCursorItemReader<>();
        reader.setSql("SELECT * FROM Tiempo");
        reader.setDataSource(dataSource);
        reader.setFetchSize(100);
        reader.setRowMapper(new TiempoMapper());

        return reader;
    }


    @Bean
    public Job tiempoJob(ErrorCounterComponent errorCounterComponent, TiempoRepository tiempoRepository) {
        return jobBuilderFactory.get("tiempoJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(tiempoStep(errorCounterComponent, tiempoRepository))
                .next(riesgoTiempoStep())
                .end()
                .build();
    }

    @Bean
    public Step tiempoStep(ErrorCounterComponent errorCounterComponent, TiempoRepository tiempoRepository) {

        return stepBuilderFactory.get("tiempoStep")
                .<Tiempo, Tiempo>chunk(5)
                .reader(reader())
                .reader(reader())
                .processor(tiempoItemProcessor())
                .writer(new TiempoWriter(tiempoRepository, errorCounterComponent))
                .listener(listener())
                .build();
    }

    @Bean
    public Step riesgoTiempoStep() {
        return stepBuilderFactory.get("step2")
                .<Tiempo, TiempoRiesgo>chunk(5)
                .reader(tiempoReaderDataBase())
                .processor(tiempoRiesgoItemProcessor())
                .writer(tiempoRiesgoItemWriter())
                .build();
    }

}
