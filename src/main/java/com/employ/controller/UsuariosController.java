package com.employ.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.employ.controller.copy.model.model.Usuarios;
import com.employ.services.IUsuariosService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private IUsuariosService usuariosService;

     
    @GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Usuarios> lista = usuariosService.buscarTodos();
		model.addAttribute("listUsuarios", lista);
    	return "usuarios/listUsuarios";
	}
    
    @GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes redirectAttributes) {		    	
		usuariosService.eliminar(idUsuario);
		redirectAttributes.addFlashAttribute("msg", "El usuario fue eliminado");
		return "redirect:/usuarios/index";
	}
}
