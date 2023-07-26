package com.employ.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.employ.controller.copy.model.model.*;

@Service
public class CategoriasServiceImpl implements ICategoriasService{

    private List<Categorias> lista;

    public CategoriasServiceImpl(){
        lista = new LinkedList<Categorias>();
        try {
            Categorias categorias1 = new Categorias();
            categorias1.setId(1);
            categorias1.setNombre("Sistemas");
            categorias1.setDescripcion("Esta categoria es del apartado tecnologico");
            lista.add(categorias1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    public void guardar(Categorias categorias) {
        lista.add(categorias);
    }

    public List<Categorias> buscarTodas() {
        return lista;
    }

    public Categorias buscarPorId(Integer idCategoria) {
        for (Categorias c: lista){
            if(c.getId() == idCategoria){
                return c;
            }
        }
        return null;
    }

    @Override
    public void eliminar(Integer idCategoria) {
        lista.remove(idCategoria);
    }
    
}
