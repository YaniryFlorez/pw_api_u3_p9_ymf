package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.matricula.application.Representation.HijoRepresentation;
import uce.edu.web.api.matricula.domain.Hijo;
import uce.edu.web.api.matricula.infraestructure.HijoRepository;

@ApplicationScoped
public class HijoService {
   @Inject
    private HijoRepository hijoRepository;
 
    public List<HijoRepresentation> buscarPorIdEstudiante(Integer idEstudiante) {
        List<HijoRepresentation> lista = new ArrayList<>();
 
        for (Hijo hijo : this.hijoRepository.buscarPorIdEstudiante(idEstudiante)) {
            lista.add(this.mapperToHR(hijo));
        }
        return lista;
    }
 
    private HijoRepresentation mapperToHR(Hijo hijo) {
        HijoRepresentation hijoRepresentation = new HijoRepresentation();
        hijoRepresentation.id = hijo.id;
        hijoRepresentation.nombre = hijo.nombre;    
        hijoRepresentation.apellido = hijo.apellido;
        return hijoRepresentation;
    }
}
