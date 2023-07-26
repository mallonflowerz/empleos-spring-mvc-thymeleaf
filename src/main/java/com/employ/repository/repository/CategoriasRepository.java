package com.employ.repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employ.controller.copy.model.model.Categorias;

public interface CategoriasRepository extends JpaRepository<Categorias, Integer> {
    
}
