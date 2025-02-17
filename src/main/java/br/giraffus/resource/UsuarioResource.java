package br.giraffus.resource;


import br.giraffus.dto.UsuarioDTO;
import br.giraffus.dto.UsuarioResponseDTO;
import br.giraffus.model.EntityClass;
import br.giraffus.model.Usuario;
import br.giraffus.repository.UsuarioRepository;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioRepository repository;

    @POST
    @PermitAll
    @Transactional
    public Response createUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome("naommmdsad");
        repository.persist(usuario);

        return Response.ok(UsuarioResponseDTO.toDTO(usuario)).build();
    }

    @GET
    @PermitAll
    public Response getAllUsuarios() {
        List<Usuario> usuarios = repository.listAll();
        return Response.ok(usuarios.stream().filter(EntityClass::getAtivo)
                .sorted(Comparator.comparing(EntityClass::getId).reversed()).map(UsuarioResponseDTO::toDTO).toList()).build();
    }

    @GET
    @PermitAll
    @Path("/{id}")
    public Response getUsuario(@PathParam("id") Long id) {
        Usuario usuario = repository.findById(id);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response.ok(UsuarioResponseDTO.toDTO(usuario)).build();
    }

    @PUT
    @PermitAll
    @Transactional
    @Path("/{id}")
    public Response updateUsuario(@PathParam("id") Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = repository.findById(id);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
        if(usuarioDTO.nome() != null) {
            usuario.setNome(usuarioDTO.nome());
        }
        repository.persist(usuario);

        return Response.ok().build();
    }

    @PATCH
    @PermitAll
    @Transactional
    @Path("/{id}")
    public Response deleteUsuario(@PathParam("id") Long id) {
        Usuario usuario = repository.findById(id);
        if (!usuario.getAtivo()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
        usuario.setAtivo(false);

        return Response.ok().build();
    }
}

