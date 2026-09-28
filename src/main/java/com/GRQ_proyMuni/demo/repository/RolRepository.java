package com.GRQ_proyMuni.demo.repository;

import com.GRQ_proyMuni.demo.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
}