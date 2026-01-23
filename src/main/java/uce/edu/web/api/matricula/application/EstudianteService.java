package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

@ApplicationScoped

public class EstudianteService {

    @Inject
    EstudianteRepository estudianteRepository;

    public List<Estudiante> listarTodos() {
        return estudianteRepository.listAll();
    }

    public Estudiante consulEstudiantePorId(Integer id) {
        return estudianteRepository.findById(id.longValue());
    }

    @Transactional
    public void crearEstudiante(Estudiante estudiante) {
        estudianteRepository.persist(estudiante);
    }

    @Transactional
    public void actualizarEstudiante(Integer id, Estudiante estudiante) {
        Estudiante estu = this.consulEstudiantePorId(id);
        estu.nombre = estudiante.nombre;
        estu.apellido = estudiante.apellido;
        estu.fechaNacimiento = estudiante.fechaNacimiento;
        estu.provincia = estudiante.provincia;
        // se actualiza automaticamente por dirty checking
    }

    @Transactional
    public void actualizarEstudianteParcial(Integer id, Estudiante estudiante) {
        Estudiante estu = this.consulEstudiantePorId(id);
        if (estudiante.nombre != null) {
            estu.nombre = estudiante.nombre;
        }
        if (estudiante.apellido != null) {
            estu.apellido = estudiante.apellido;
        }
        if (estudiante.fechaNacimiento != null) {
            estu.fechaNacimiento = estudiante.fechaNacimiento;
        }
        if(estudiante.provincia != null) {
            estu.provincia = estudiante.provincia;
        }   
        // se actualiza automaticamente por dirty checking
    }
    @Transactional
    public void eliminarEstudiante(Integer id) {
        estudianteRepository.deleteById(id.longValue());
    }

   /* public List<Estudiante> buscarPorProvincia(String provincia) {
        return estudianteRepository.find("provincia", provincia).list();
    }*/

     public List<Estudiante> buscarPorProvincia(String provincia, String genero) {
        return estudianteRepository.find("provincia = ?1 and genero = ?2", provincia, genero).list();
    }


}