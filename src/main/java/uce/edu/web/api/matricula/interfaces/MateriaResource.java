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
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
public class MateriaResource {

    @Inject
    MateriaService materiaService;

    @POST
    @Path("")
    public void guardarMateria(Materia materia) {
        materiaService.crearMateria(materia);
    }

    @GET
    @Path("")
    public List<Materia> listarTodas() {
        return materiaService.listarTodas();
    }   

    @GET
    @Path("/{id}")
    public Materia consulMateriaPorId(@PathParam("id") Integer iden) {
        return materiaService.consulMateriaPorId(iden);
    }

    @PUT
    @Path("/{id}")
    public void actualizarMateria(@PathParam("id") Integer iden, Materia materia) {
        materiaService.actualizarMateria(iden, materia);
    }

    @PATCH
    @Path("/{id}")
    public void actualizarMateriaParcial(@PathParam("id") Integer iden, Materia materia) {
        materiaService.actualizarMateriaParcial(iden, materia);
    }

    @DELETE
    @Path("/{id}")
    public void borrarMateria(@PathParam("id") Integer iden) {
        materiaService.eliminarMateria(iden);
    }
    
    @GET
    @Path("/{creditos}")
    public List<Materia> listarMateriasPorCreditos(@PathParam("creditos") String creditos) {
        return materiaService.listarMateriasPorCreditos(creditos);
    }

    @DELETE
    @Path("/{codigo}")
    public void borrarMateriasPorCodigo(@PathParam("codigo") String codigo) {
        materiaService.elimnarPorCodigo(codigo);
    }

}