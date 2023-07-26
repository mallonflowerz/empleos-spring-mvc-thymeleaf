package com.employ.controller.copy;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.employ.controller.copy.model.model.*;
import com.employ.services.ICategoriasService;
import com.employ.services.IUsuariosService;
import com.employ.services.IVacantesService;

@Controller
public class HomeController {

	@Autowired
	private ICategoriasService categoriasService;

	@Autowired
	private IVacantesService serviceVacantes;

	@Autowired
	private IUsuariosService usuariosService;

	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Vacantes> lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);

		return "tabla";
	}

	@GetMapping("/detalle")
	public String mostrarDetalle(Model model) {
		Vacantes vacantes = new Vacantes();
		vacantes.setName("Ingeniero de Comunicaciones");
		vacantes.setDescripcion("Se solicita ingeniero para dar soporte a internet");
		vacantes.setFecha(new Date());
		vacantes.setSalario(9700.0);

		model.addAttribute("vacantes", vacantes);

		return "detalle";
	}

	@GetMapping("/listado")
	public String mostrarList(Model model) {
		List<String> lista = new LinkedList<String>();
		lista.add("Ingeniero de Sistemas");
		lista.add("Auxiliar en Contabilidad");
		lista.add("Ingeniero Industrial");
		lista.add("Arquitecto");

		model.addAttribute("empleos", lista);
		return "listado";
	}

	@GetMapping("/")
	public String mostrarHome(Model model) {
		return "home";
	}

	@ModelAttribute
	public void setGenericos(Model model) {
		Vacantes vacanteSearch = new Vacantes();
		vacanteSearch.reset();
		model.addAttribute("search", vacanteSearch);
		model.addAttribute("vacantes", serviceVacantes.buscarDestacadas());
		model.addAttribute("categorias", categoriasService.buscarTodas());
	}

	@GetMapping("/search")
	public String buscar(@ModelAttribute("search") Vacantes vacantes, Model model){
		System.out.println("Bucando: "+ vacantes);

		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());
		Example<Vacantes> examples = Example.of(vacantes, matcher);
		List<Vacantes> lista = serviceVacantes.buscarByExample(examples);
		model.addAttribute("vacantes", lista);
		return "home";
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@GetMapping("/signup")
	public String registrarse(Usuarios usuario, Model model) {
		model.addAttribute("usuarios", usuariosService.buscarTodos());
		return "usuarios/formRegistro";
	}
	
	@PostMapping("/signup")
	public String guardarRegistro(Usuarios usuario, RedirectAttributes redirectAttributes, BindingResult result, Model model) {
		redirectAttributes.addFlashAttribute("msg", "Registro exitoso");
		model.addAttribute("error", result);
		usuario.setEstatus(1);
		usuario.setFechaRegistro(new Date());
		if (result.hasErrors()){
			for (ObjectError error: result.getAllErrors()){
                System.out.println("Ocurrio un error: "+error.getDefaultMessage());
            }
            return "usuarios/formRegistro";
		}
		usuariosService.guardar(usuario);
		return "redirect:/usuarios/index";
	}
}
