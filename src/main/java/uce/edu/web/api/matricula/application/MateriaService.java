package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infraestructure.MateriaRepository;


@ApplicationScoped
public class MateriaService {

    @Inject
    MateriaRepository materiaRepository;

    @Transactional
    public void crearMateria(Materia materia) {
        materiaRepository.persist(materia);
    }

    public List<Materia> listarTodas() {
        return materiaRepository.listAll();
    }

    public Materia consulMateriaPorId(Integer id) {
        return materiaRepository.findById(id.longValue());
    }
    @Transactional
    public void actualizarMateria(Integer id, Materia materia) {
        Materia mat = this.consulMateriaPorId(id);
        mat.nombre = materia.nombre;
        mat.codigo = materia.codigo;
        mat.creditos = materia.creditos;
        mat.descripcion = materia.descripcion;
        
        // se actualiza automaticamente por dirty checking
    }

    @Transactional
    public void actualizarMateriaParcial(Integer id, Materia materia) {
        Materia mat = this.consulMateriaPorId(id);
        if (materia.nombre != null) {
            mat.nombre = materia.nombre;
        }
        if (materia.codigo != null) {
            mat.codigo = materia.codigo;
        }
        if (materia.creditos != null) {
            mat.creditos = materia.creditos;
        }
        if (materia.descripcion != null) {
            mat.descripcion = materia.descripcion;
        }
        // se actualiza automaticamente por dirty checking
    }
    
    @Transactional
    public void eliminarMateria(Integer id) {
        materiaRepository.deleteById(id.longValue());
    }

    public List<Materia> listarMateriasPorCreditos(String creditos) {
        return materiaRepository.list("creditos", creditos);
    }
    
    @Transactional
    public void elimnarPorCodigo(String codigo) {
        materiaRepository.delete("codigo", codigo);
    }   

}
