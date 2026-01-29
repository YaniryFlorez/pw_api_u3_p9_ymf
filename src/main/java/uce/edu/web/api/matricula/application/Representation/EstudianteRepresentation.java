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

}
