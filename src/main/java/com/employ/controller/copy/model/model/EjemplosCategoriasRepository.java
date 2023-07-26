package com.employ.controller.copy.model.model;

import com.employ.repository.repository.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

public class EjemplosCategoriasRepository {

    @Autowired
    private CategoriasRepository categoriasRepository;

    private void conteo() {
        long cont = categoriasRepository.count();
        System.out.println(cont);

    }

    private void guardar() {
        Categorias categorias = new Categorias();
        categorias.setNombre("Yo");
        categorias.setDescripcion("YH!");
        categoriasRepository.save(categorias);
        System.out.println(categorias);
    }

    private void eliminar() {
        int idCategoria = 1;
        categoriasRepository.deleteById(idCategoria);
    }

    private void eliminarTodo() {
        categoriasRepository.deleteAll();
    }

    private void encontrarPorIds() {
        List<Integer> ids = new LinkedList<Integer>();
        ids.add(1);
        ids.add(4);
        ids.add(6);
        ids.add(8);
        ids.add(10);
        Iterable<Categorias> categorias = categoriasRepository.findAllById(ids);
        for (Categorias c : categorias) {
            System.out.println(c);
        }
    }

    private void buscarTodos() {
        Iterable<Categorias> c = categoriasRepository.findAll();
        for (Categorias cat : c) {
            System.out.println(cat);
        }

    }

    private void modificar() {
        Optional<Categorias> option = categoriasRepository.findById(1);
        if (option.isPresent()) {
            Categorias categoriasTmp = option.get();
            categoriasTmp.setNombre("Ingeniera de Sofware");
            categoriasTmp.setDescripcion("Desarrollo de sistemas");
            categoriasRepository.save(categoriasTmp);
            System.out.println(option.get());
        } else {
            System.out.println("No se encontro na");
        }
    }

    private void existeId() {
        int id = 1;
        boolean existe = categoriasRepository.existsById(id);
        if (existe) {
            System.out.println("La categoria SI existe: " + existe);
        } else {
            System.out.println("La categoria NO existe: " + existe);
        }
    }

    private void buscarPorId() {
        Optional<Categorias> option = categoriasRepository.findById(1);
        if (option.isPresent()) {
            System.out.println(option.get());
        } else {
            System.out.println("No se encontro na");
        }

    }

    private List<Categorias> getListCategorias() {
        List<Categorias> lista = new LinkedList<Categorias>();

        Categorias categoria1 = new Categorias();
        categoria1.setNombre("Hola");
        categoria1.setDescripcion("Hola");

        Categorias categoria2 = new Categorias();
        categoria2.setNombre("Hola 2");
        categoria2.setDescripcion("Hola 2");

        Categorias categoria3 = new Categorias();
        categoria3.setNombre("Hola 3");
        categoria3.setDescripcion("Hola 3");

        lista.add(categoria1);
        lista.add(categoria2);
        lista.add(categoria3);

        return lista;
    }

    private void guardarTodas() {
        List<Categorias> categorias = getListCategorias();
        categoriasRepository.saveAll(categorias);
    }

    private void pedirDatos() {
        Scanner scanner = new Scanner(System.in);

        Categorias categorias = new Categorias();
        System.out.println("Ingrese nombre de categoria");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese descripcion de categoria");
        String descripcion = scanner.nextLine();
        while (nombre.isEmpty() || descripcion.isEmpty()) {
            System.out.println("El nombre esta vacio");
            System.out.println("Ingrese nombre de categoria");
            nombre = scanner.nextLine();
            System.out.println("Ingrese descripcion de categoria");
            descripcion = scanner.nextLine();
        }
        categorias.setNombre(nombre);
        categorias.setDescripcion(descripcion);
        categoriasRepository.save(categorias);

    }

}
