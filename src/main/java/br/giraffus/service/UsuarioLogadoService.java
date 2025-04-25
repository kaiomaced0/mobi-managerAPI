package br.giraffus.service;

import jakarta.ws.rs.core.Response;

public interface UsuarioLogadoService {
    Response getPerfilUsuarioLogado();
}