package com.employ.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.employ.controller.copy.model.model.*;
import com.employ.services.ICategoriasService;
import com.employ.services.IVacantesService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;




@Controller
@RequestMapping(value="/categorias")
public class CategoriasController {
    @Autowired
    private IVacantesService serviceVacantes;

    @Autowired
    private ICategoriasService categoriasService; 

    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String mostrarIndex(Model model) {
        List<Categorias> categorias = categoriasService.buscarTodas();
        model.addAttribute("listCategorias", categorias);
        return "categorias/listCategorias";
    }

    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String crear() {
        return "categorias/formCategorias"; 
    }

    @RequestMapping(value="/save", method=RequestMethod.POST)
    public String guardar(Model model, Categorias categorias, BindingResult result, RedirectAttributes redirectAttributes) {
        categoriasService.guardar(categorias);
        redirectAttributes.addFlashAttribute("msg", "Registro Exitoso");
        model.addAttribute("error", result);
        if (result.hasErrors()){
            for (ObjectError error: result.getAllErrors()){
                System.out.println("Ocurrio un error: "+error.getDefaultMessage());
            }
            return "categorias/formCategorias";
        }
        System.out.println("Categorias: " + categorias);
        return "redirect:/categorias/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String editar(@PathVariable("id") int idCategoria, Model model){
        Categorias categorias = categoriasService.buscarPorId(idCategoria);
        model.addAttribute("categorias", categorias);
        setGenericos(model);
        return "categorias/formCategorias";
    }

    @GetMapping(value="/delete/{id}")
    public String eliminar(@PathVariable("id") Integer idCategoria, RedirectAttributes redirectAttributes, Model model) {
        categoriasService.eliminar(idCategoria);
        redirectAttributes.addFlashAttribute("msg", "La categoria fue eliminada");
        return "redirect:/categorias/index";
    }

    @ModelAttribute
    public void setGenericos(Model model){
        model.addAttribute("categorias", categoriasService.buscarTodas());
    }
    
    
}
