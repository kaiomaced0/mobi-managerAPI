package br.giraffus.service;

import java.util.List;

import br.giraffus.dto.AuthUsuarioDTO;
import br.giraffus.dto.UsuarioDTO;
import br.giraffus.dto.UsuarioResponseDTO;
import br.giraffus.model.Usuario;
import jakarta.ws.rs.core.Response;

public interface UsuarioService {
    List<UsuarioResponseDTO> getAll();
    List<UsuarioResponseDTO> getFuncionarios();
    List<UsuarioResponseDTO> getNome(String nome);
    Usuario findByLoginAndSenha(AuthUsuarioDTO auth);
    Usuario findByEmailAndSenha(AuthUsuarioDTO auth);
    UsuarioResponseDTO getId(Long id);
    UsuarioResponseDTO insert(UsuarioDTO usuario);
    Response insertFuncionario(UsuarioDTO usuario);
    Response delete(Long id);
}