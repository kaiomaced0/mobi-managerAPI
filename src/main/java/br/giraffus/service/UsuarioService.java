package br.giraffus.service;

import br.giraffus.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UsuarioService {
    @Inject
    UsuarioRepository repository;
}
