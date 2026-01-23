package uce.edu.web.api.matricula.domain;

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
@Table(name = "materias")
@SequenceGenerator(name = "seq_materia", sequenceName = "seq_materia", allocationSize = 1)
public class Materia extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_materia")
    public Integer id;
    public String codigo;
    public String nombre;
    public Integer creditos;
    public String descripcion;

}
