package com.employ.services;

import java.util.List;

import com.employ.controller.copy.model.model.*;

public interface ICategoriasService {
    void guardar(Categorias categorias);
    List<Categorias> buscarTodas();
    Categorias buscarPorId(Integer idCategoria);
    void eliminar(Integer idCategoria);
}
