package com.employ.services.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employ.controller.copy.model.model.Usuarios;
import com.employ.repository.repository.UsuariosRepository;
import com.employ.services.IUsuariosService;

@Service
public class UsuariosServiceJpa implements IUsuariosService{

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    public void guardar(Usuarios usuario) {
        usuariosRepository.save(usuario);
    }

    @Override
    public void eliminar(Integer idUsuario) {
        usuariosRepository.deleteById(idUsuario);
    }

    @Override
    public List<Usuarios> buscarTodos() {
        return usuariosRepository.findAll();
    }
    
}
