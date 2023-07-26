package com.employ.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.employ.controller.copy.model.model.*;
import com.employ.services.ICategoriasService;
import com.employ.services.IVacantesService;
import com.employ.util.Utileria;

@Controller
@RequestMapping(value="/vacantes")
public class VacantesController {
    @Autowired
    private IVacantesService serviceVacantes;

    @Value("${empleosapp.ruta.imagenes}")
    private String ruta;

    @Autowired
    private ICategoriasService categoriasService;

    @GetMapping("/index")
    public String mostrarIndex(Model model){
        List<Vacantes> vacante = serviceVacantes.buscarTodas();
        model.addAttribute("listVacantes", vacante);
        System.out.println(vacante);
        return "vacantes/listVacantes";
    }

    @GetMapping("/create")
    public String crear(Vacantes vacantes, Model model){
        setGenericos(model);
        return "vacantes/formVacante";
    }
    
    @PostMapping("/save")
    public String guardar(Model model, Vacantes vacantes, BindingResult result, RedirectAttributes redirectAttributes, @RequestParam("archivoImagen") MultipartFile multiPart){
        redirectAttributes.addFlashAttribute("msg", "Registro Exitoso");
        model.addAttribute("error", result);
        if (result.hasErrors()){
            for (ObjectError error: result.getAllErrors()){
                System.out.println("Ocurrio un error: "+error.getDefaultMessage());
            }
            return "vacantes/formVacante"; 
        }
        if (!multiPart.isEmpty()) {
            //String ruta = "/empleos/img-vacantes/"; // Linux/MAC
            // String ruta = "c:/empleos/img-vacantes/"; // Windows
            String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
            if (nombreImagen != null){ // La imagen si se subio
            // Procesamos la variable nombreImagen
            vacantes.setImages(nombreImagen); 
            }
            }
        serviceVacantes.guardar(vacantes);
        System.out.println("Vacantes: "+ vacantes);
        return "redirect:/vacantes/index";
    }

    @GetMapping("/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page){
        Page<Vacantes> lista = serviceVacantes.buscarTodas(page);
        model.addAttribute("listVacantes", lista);
        return "vacantes/listVacantes";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") Integer idVacante, Model model){
        Vacantes vacantes = serviceVacantes.buscarPorId(idVacante);
        model.addAttribute("vacantes", vacantes);
        setGenericos(model);
        return "vacantes/formVacante";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idVacante, RedirectAttributes redirectAttributes, Model model){
        System.out.println("Borrando vacante con ID: "+ idVacante);
        serviceVacantes.eliminar(idVacante);
        redirectAttributes.addFlashAttribute("msg", "La vacante fue eliminada");
        return "redirect:/vacantes/index";
    } 

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") int idVacante, Model model) {
        Vacantes vacante = serviceVacantes.buscarPorId(idVacante);
        model.addAttribute("vacantes", vacante);
        System.out.println("Vacante: "+ vacante);
        return "detalle";
    }

    @ModelAttribute
    public void setGenericos(Model model){
        model.addAttribute("categorias", categoriasService.buscarTodas());
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));
    }
}
