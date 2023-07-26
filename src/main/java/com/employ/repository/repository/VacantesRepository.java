package com.employ.repository.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.employ.controller.copy.model.model.Vacantes;

import java.util.List;

public interface VacantesRepository extends JpaRepository<Vacantes, Integer> {

    List<Vacantes> findByEstatus(String estatus);

    List<Vacantes> findByDestacadoAndEstatusOrderByIdDesc(Integer destacado, String estatus);

    List<Vacantes> findBySalarioBetween(double s1, double s2);

    List<Vacantes> findByEstatusIn(String[] estatus);

    Page<Vacantes> findAll(Pageable pageable);

}
