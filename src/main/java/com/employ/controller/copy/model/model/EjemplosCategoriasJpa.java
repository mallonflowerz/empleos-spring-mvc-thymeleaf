package com.employ.controller.copy.model.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import com.employ.repository.repository.*;

public class EjemplosCategoriasJpa {

    @Autowired
	private CategoriasRepository categoriasRepository;

    public void buscarTodosJpa() {
		List<Categorias> categoriasList = categoriasRepository.findAll();
		for (Categorias c : categoriasList) {
			System.out.println(c.getId() + " " + c.getNombre());
		}
	}

	private void borrarTodoEnBloque() {
		categoriasRepository.deleteAllInBatch();
	}

	private void buscarTodosOrdenados() {
		Iterable<Categorias> c = categoriasRepository.findAll(Sort.by("nombre"));
		for (Categorias cat : c) {
			System.out.println(cat.getId() + " " + cat.getNombre());
		}
	}

	private void buscarTodoPaginacion() {
		Page<Categorias> pagCategoria = categoriasRepository.findAll(PageRequest.of(0, 5));
		System.out.println("Total Registros: " + pagCategoria.getTotalElements());
		System.out.println("Total Paginas: " + pagCategoria.getTotalPages());
		for (Categorias c : pagCategoria.getContent()) {
			System.out.println(c.getId() + " " + c.getNombre());
		}
	}

	private void buscarTodoPaginacionOrdenados() {
		Page<Categorias> pagCategoria = categoriasRepository.findAll(PageRequest.of(0, 5, Sort.by("nombre")));
		System.out.println("Total Registros: " + pagCategoria.getTotalElements());
		System.out.println("Total Paginas: " + pagCategoria.getTotalPages());
		for (Categorias c : pagCategoria.getContent()) {
			System.out.println(c.getId() + " " + c.getNombre());
		}
	}
}
