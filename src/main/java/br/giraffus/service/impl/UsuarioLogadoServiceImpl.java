package br.giraffus.service.impl;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.giraffus.dto.UsuarioResponseDTO;
import br.giraffus.model.Usuario;
import br.giraffus.repository.UsuarioRepository;
import br.giraffus.service.UsuarioLogadoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class UsuarioLogadoServiceImpl implements UsuarioLogadoService {

    private static final Logger LOG = Logger.getLogger(UsuarioLogadoServiceImpl.class);

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    public Response getPerfilUsuarioLogado() {
        String login = jsonWebToken.getSubject();
        LOG.info(jsonWebToken.getClaimNames());
        try {
            Usuario u = usuarioRepository.findByLogin(login);
            LOG.info("Requisição UsuarioLogado.getPerfilUsuarioLogado()");
            return Response.ok(UsuarioResponseDTO.toDTO(u)).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição UsuarioLogado.getPerfilUsuarioLogado()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }
}