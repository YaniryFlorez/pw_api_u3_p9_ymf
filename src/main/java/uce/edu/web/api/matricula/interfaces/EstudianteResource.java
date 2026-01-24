package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HijoService;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.domain.Hijo;

@Path("/estudiantes")
public class EstudianteResource {
    @Inject
    EstudianteService estudianteService;
    HijoService hijoService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estudiante> listarTodos() {
        System.out.println("listarTodos");
        return estudianteService.listarTodos();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Estudiante consulEstudiantePorId(@PathParam("id") Integer iden) {
        return estudianteService.consulEstudiantePorId(iden);
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarEstudiante(Estudiante estudiante) {
        estudianteService.crearEstudiante(estudiante);
        return Response.status(Response.Status.CREATED).entity(estudiante).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void ctualizarEstudiante(@PathParam("id") Integer iden, Estudiante estudiante) {
        estudianteService.actualizarEstudiante(iden, estudiante);
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)   
    public Response actualizarEstudianteParcial(@PathParam("id") Integer iden, Estudiante estudiante) {
        estudianteService.actualizarEstudianteParcial(iden, estudiante);
        return Response.status(209).entity(null).build();
    }

    @DELETE
    @Path("/{id}")
    public void borrarEstudiante(@PathParam("id") Integer iden) {
        estudianteService.eliminarEstudiante(iden);
    }

    @GET
    @Path("/provincia/genero")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estudiante> buscarPorProvincia(@QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {
        System.out.println("buscarPorProvincia: " + provincia + ", genero: " + genero);
        return estudianteService.buscarPorProvincia(provincia, genero);
    }
    
    @GET
    @Path("/{id}/hijos")
    public List<Hijo> obtenerHijosEstudiante(@PathParam("id") Integer iden) {
        return hijoService.buscarPorIdEstudiante(iden);
    }

}
