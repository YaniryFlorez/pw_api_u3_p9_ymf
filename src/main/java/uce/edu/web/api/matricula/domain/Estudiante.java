package uce.edu.web.api.matricula.domain;

import java.time.LocalDate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@Entity
@Table(name = "estudiantes")
@SequenceGenerator(name = "seq_estudiante", sequenceName = "seq_estudiante", allocationSize = 1)
public class Estudiante extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_estudiante")
    public Integer id;
    public String nombre;
    public String apellido;
    public LocalDate fechaNacimiento;
    public String provincia;
    public String genero;

}
