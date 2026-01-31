package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.application.Representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

@ApplicationScoped

public class EstudianteService {

    @Inject
    EstudianteRepository estudianteRepository;

    public List<EstudianteRepresentation> listarTodos() {
        List<EstudianteRepresentation> listaEstudiantesR = new ArrayList<>();
        for (Estudiante est : this.estudianteRepository.listAll()) {
            listaEstudiantesR.add(this.mapperToER(est));
        }
        return listaEstudiantesR;
    }

    public EstudianteRepresentation consulEstudiantePorId(Integer id) {
        return this.mapperToER(this.estudianteRepository.findById(id.longValue()));
    }

    @Transactional
    public void crearEstudiante(EstudianteRepresentation estu) {
        estudianteRepository.persist(this.mapperToEstudiante(estu));
    }

    @Transactional
    public EstudianteRepresentation actualizarEstudiante(Integer id, EstudianteRepresentation estudiante) {
        Estudiante estu = this.estudianteRepository.findById(id.longValue());
        if (estu == null) {
            return null;
        }
        estu.nombre = estudiante.nombre;
        estu.apellido = estudiante.apellido;
        estu.fechaNacimiento = estudiante.fechaNacimiento;
        estu.provincia = estudiante.provincia;
        estu.genero = estudiante.genero;
        return this.mapperToER(estu);
        // se actualiza automaticamente por dirty checking
    }

    @Transactional
    public void actualizarEstudianteParcial(Integer id, EstudianteRepresentation estudiante) {
        Estudiante estu = this.estudianteRepository.findById(id.longValue());
       
       if(estu != null){
         if (estudiante.nombre != null) {
            estu.nombre = estudiante.nombre;
        }
        if (estudiante.apellido != null) {
            estu.apellido = estudiante.apellido;
        }
        if (estudiante.fechaNacimiento != null) {
            estu.fechaNacimiento = estudiante.fechaNacimiento;
        }
        if (estudiante.provincia != null) {
            estu.provincia = estudiante.provincia;
        }
        // se actualiza automaticamente por dirty checking
    }
    }

    @Transactional
    public void eliminarEstudiante(Integer id) {
        estudianteRepository.deleteById(id.longValue());
    }

    /*
     * public List<Estudiante> buscarPorProvincia(String provincia) {
     * return estudianteRepository.find("provincia", provincia).list();
     * }
     */

    public List<EstudianteRepresentation> buscarPorProvincia(String provincia, String genero) {
        List<EstudianteRepresentation> listaEstudiantesR = new ArrayList<>();
        for (Estudiante estu : estudianteRepository.find("provincia = ?1 and genero = ?2", provincia, genero).list()) {
            listaEstudiantesR.add(this.mapperToER(estu));
        }
        return listaEstudiantesR;
    }

    private EstudianteRepresentation mapperToER(Estudiante est) {
        EstudianteRepresentation estuR = new EstudianteRepresentation();
        estuR.id = est.id;
        estuR.nombre = est.nombre;
        estuR.apellido = est.apellido;
        estuR.fechaNacimiento = est.fechaNacimiento;
        estuR.provincia = est.provincia;
        estuR.genero = est.genero;
        return estuR;
    }

    private Estudiante mapperToEstudiante(EstudianteRepresentation est) {
        Estudiante estuR = new Estudiante();
        estuR.id = est.id;
        estuR.nombre = est.nombre;
        estuR.apellido = est.apellido;
        estuR.fechaNacimiento = est.fechaNacimiento;
        estuR.provincia = est.provincia;
        estuR.genero = est.genero;
        return estuR;

    }

}