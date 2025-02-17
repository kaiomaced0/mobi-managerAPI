package br.giraffus.service;

import br.giraffus.model.Usuario;

public interface TokenJwt {
    public String generateJwt(Usuario usuario);
}
