package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.domain.Estudiante;

@Path("/estudiantes")
public class EstudianteResource {
    @Inject
    EstudianteService estudianteService;

    @GET
    @Path("/todos")
    public List<Estudiante> listarTodos() {
        return estudianteService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Estudiante consulEstudiantePorId(@PathParam("id") Integer iden) {
        return estudianteService.consulEstudiantePorId(iden);
    }

    @POST
    @Path("/guardar")
    public void guardarEstudiante(Estudiante estudiante) {
        estudianteService.crearEstudiante(estudiante);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizarEstudiante(@PathParam("id") Integer iden, Estudiante estudiante) {
        estudianteService.actualizarEstudiante(iden, estudiante);
    }

    @PATCH
    @Path("/actualizarParcial/{id}")
    public void actualizarEstudianteParcial(@PathParam("id") Integer iden, Estudiante estudiante) {
        estudianteService.actualizarEstudianteParcial(iden, estudiante);
    }

    @DELETE
    @Path("/borrar/{id}")
    public void borrarEstudiante(@PathParam("id") Integer iden) {
        estudianteService.eliminarEstudiante(iden);
    }
    
}
