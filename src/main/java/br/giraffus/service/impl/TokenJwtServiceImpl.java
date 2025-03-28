package br.giraffus.service.impl;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;

import br.giraffus.enums.usuario.Perfil;
import br.giraffus.model.Usuario;
import br.giraffus.service.TokenJwtService;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TokenJwtServiceImpl implements TokenJwtService {

    private static final Duration EXPIRATION_TIME = Duration.ofHours(24);

    public static final Logger LOG = Logger.getLogger(TokenJwtServiceImpl.class);

    @Override
    public String generateJwt(Usuario usuario) {

        try {
            Instant now = Instant.now();

            Instant expiryDate = now.plus(EXPIRATION_TIME);

            Set<String> roles = usuario.getPerfis()
                    .stream().map(Perfil::getLabel)
                    .collect(Collectors.toSet());

            LOG.info("Requisição TokenJwt.generateJwt()");

            return Jwt.issuer("gika-jwt")
                    .subject(usuario.getLogin())
                    .groups(roles)
                    .expiresAt(expiryDate)
                    .sign();

        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição TokenJwt.generateJwt()");
            return null;
        }

    }

}
