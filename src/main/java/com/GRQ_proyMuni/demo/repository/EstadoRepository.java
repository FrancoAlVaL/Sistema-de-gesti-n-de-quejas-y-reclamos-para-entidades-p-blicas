package com.GRQ_proyMuni.demo.repository;

import com.GRQ_proyMuni.demo.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}