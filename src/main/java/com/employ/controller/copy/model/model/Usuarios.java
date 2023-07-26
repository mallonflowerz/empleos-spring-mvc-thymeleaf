package com.employ.controller.copy.model.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String email;
    private String username;
    private String password;
    private Integer estatus;
    private Date fechaRegistro;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="usuarioperfil", joinColumns=@JoinColumn(name="idUsuario"), inverseJoinColumns = @JoinColumn(name="idPerfil"))
    private List<Perfiles> perfiles;     

    public void agregar(Perfiles perfil){
		if (perfiles == null){
            perfiles = new LinkedList<Perfiles>();
        }
        perfiles.add(perfil);
	}
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public List<Perfiles> getPerfiles() {
        return perfiles;
    }
    public void setPerfiles(List<Perfiles> perfiles) {
        this.perfiles = perfiles;
    }
    @Override
    public String toString() {
        return "Usuarios [id=" + id + ", nombre=" + nombre + ", email=" + email + ", username=" + username
                + ", password=" + password + ", estatus=" + estatus + ", fechaRegistro=" + fechaRegistro + ", perfiles="
                + perfiles + "]";
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }
    
    

    
}
