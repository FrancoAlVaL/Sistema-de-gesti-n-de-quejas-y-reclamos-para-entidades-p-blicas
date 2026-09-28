package com.GRQ_proyMuni.demo.repository;

import com.GRQ_proyMuni.demo.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {
    // Métodos básicos automáticamente
}
