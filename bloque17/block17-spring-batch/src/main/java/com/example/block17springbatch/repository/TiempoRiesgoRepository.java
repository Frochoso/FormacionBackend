package com.example.block17springbatch.repository;

import com.example.block17springbatch.domain.TiempoRiesgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiempoRiesgoRepository extends JpaRepository<TiempoRiesgo, Integer> {
}
