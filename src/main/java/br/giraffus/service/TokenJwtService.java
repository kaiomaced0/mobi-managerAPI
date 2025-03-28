package br.giraffus.service;

import br.giraffus.model.Usuario;

public interface TokenJwtService {
    public String generateJwt(Usuario usuario);
}
