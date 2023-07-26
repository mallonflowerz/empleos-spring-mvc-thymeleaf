package com.employ.services.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.employ.controller.copy.model.model.Categorias;
import com.employ.repository.repository.CategoriasRepository;
import com.employ.services.ICategoriasService;

@Service
@Primary
public class CategoriasServicesJpa implements ICategoriasService{

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Override
    public void guardar(Categorias categorias) {
        categoriasRepository.save(categorias);
    }

    @Override
    public List<Categorias> buscarTodas() {
        return categoriasRepository.findAll();
    }

    @Override
    public Categorias buscarPorId(Integer idCategoria) {
        Optional<Categorias> optional = categoriasRepository.findById(idCategoria);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public void eliminar(Integer idCategoria) {
        categoriasRepository.deleteById(idCategoria);
    }
    
}
