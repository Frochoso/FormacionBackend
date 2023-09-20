package com.example.block17springbatch.repository;

import com.example.block17springbatch.domain.Tiempo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiempoRepository extends JpaRepository<Tiempo, Integer> {
}
