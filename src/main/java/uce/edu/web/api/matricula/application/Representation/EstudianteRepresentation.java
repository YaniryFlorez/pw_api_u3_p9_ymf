package uce.edu.web.api.matricula.application.Representation;

import java.time.LocalDate;
import java.util.List;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class EstudianteRepresentation {
    public Integer id;
    public String nombre;
    public String apellido;
    public LocalDate fechaNacimiento;
    public String provincia;
    public String genero;

   // http:/localhost:8080/estudiantes/1/hijos
   private List<LinkDto> Links;
    public List<LinkDto> getLinks() {
         return Links;
    }
    public void setLinks(List<LinkDto> links) {
         this.Links = links;
    }

    //getter and setters

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
     public String getApellido() {
          return apellido;
     }
     public void setApellido(String apellido) {
          this.apellido = apellido;
     }
     public LocalDate getFechaNacimiento() {
          return fechaNacimiento;
     }
     public void setFechaNacimiento(LocalDate fechaNacimiento) {
          this.fechaNacimiento = fechaNacimiento;
     }
     public String getProvincia() {
          return provincia;
     }
     public void setProvincia(String provincia) {
          this.provincia = provincia;
     }

     public String getGenero() {
          return genero;
     }
     public void setGenero(String genero) {
          this.genero = genero;
     }


}
