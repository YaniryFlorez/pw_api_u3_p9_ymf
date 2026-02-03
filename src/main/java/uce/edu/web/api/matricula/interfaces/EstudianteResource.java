package uce.edu.web.api.matricula.interfaces;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.annotation.security.RolesAllowed;
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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HijoService;
import uce.edu.web.api.matricula.application.Representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.application.Representation.HijoRepresentation;
import uce.edu.web.api.matricula.application.Representation.LinkDto;

@Path("/estudiantes")
public class EstudianteResource {
    @Inject
    EstudianteService estudianteService;
    @Inject
    HijoService hijoService;
    //para construir URIs de hijos
    @Context
    private UriInfo uriInfo;
 
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public List<EstudianteRepresentation> listarTodos() {
        System.out.println("listarTodos");
        List<EstudianteRepresentation> lista = estudianteService.listarTodos();
        return lista.stream()
                .map(this::addLinks)
                .collect(Collectors.toList());

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    //@PermitAll
    @RolesAllowed({"admin"})
    public EstudianteRepresentation consulEstudiantePorId(@PathParam("id") Integer iden) {
        return this.addLinks(this.estudianteService.consulEstudiantePorId(iden)) ;
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public Response guardarEstudiante(EstudianteRepresentation estudiante) {
        estudianteService.crearEstudiante(estudiante);
        return Response.status(Response.Status.CREATED).entity(estudiante).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public Response actualizarEstudiante(@PathParam("id") Integer iden, EstudianteRepresentation estudiante) {
        EstudianteRepresentation actualizado = this.estudianteService.actualizarEstudiante(iden, estudiante);
        return Response.status(209).entity(actualizado).build();        
    
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)  
    @RolesAllowed({"admin"}) 
    public Response actualizarEstudianteParcial(@PathParam("id") Integer iden, EstudianteRepresentation estudiante) {
        estudianteService.actualizarEstudianteParcial(iden, estudiante);
        return Response.status(209).entity(null).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"admin"})
    public void borrarEstudiante(@PathParam("id") Integer iden) {
        estudianteService.eliminarEstudiante(iden);
    }

    @GET
    @Path("/provincia/genero")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public List<EstudianteRepresentation> buscarPorProvincia(@QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {
        System.out.println("buscarPorProvincia: " + provincia + ", genero: " + genero);
        return estudianteService.buscarPorProvincia(provincia, genero);
    }
    
    @GET
    @Path("/{id}/hijos")
    @RolesAllowed({"admin"})
    public List<HijoRepresentation> obtenerHijosEstudiante(@PathParam("id") Integer iden) {
        return hijoService.buscarPorIdEstudiante(iden);
    }

     private EstudianteRepresentation addLinks(EstudianteRepresentation estudiante) {
    String self = this.uriInfo.getBaseUriBuilder()
            .path("estudiantes")
            .path(estudiante.id.toString())
            .build()
            .toString();

    String hijos = this.uriInfo.getBaseUriBuilder()
            .path("estudiantes")
            .path(estudiante.id.toString())
            .path("hijos")
            .build()
            .toString();

    estudiante.setLinks(List.of(
            new LinkDto(self, "self"),
            new LinkDto(hijos, "hijos")
    ));

    return estudiante;
}
}
