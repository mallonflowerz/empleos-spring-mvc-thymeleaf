package com.employ.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;

import com.employ.controller.copy.model.model.*;

public interface IVacantesService {
    List<Vacantes> buscarTodas();

    Vacantes buscarPorId(Integer idVacante);

    void guardar(Vacantes vacantes);

    List<Vacantes> buscarDestacadas();

    void eliminar(Integer idVacante);

    List<Vacantes> buscarByExample(Example<Vacantes> examples);

    Page<Vacantes> buscarTodas(Pageable page);
}
