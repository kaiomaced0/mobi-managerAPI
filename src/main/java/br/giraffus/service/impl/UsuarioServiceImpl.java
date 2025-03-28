package br.giraffus.service.impl;

import java.util.List;

import br.giraffus.dto.AuthUsuarioDTO;
import br.giraffus.dto.UsuarioDTO;
import br.giraffus.dto.UsuarioResponseDTO;
import br.giraffus.model.Usuario;
import br.giraffus.repository.UsuarioRepository;
import br.giraffus.service.UsuarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {
    
    @Inject 
    UsuarioRepository repository;

    @Override
    public List<UsuarioResponseDTO> getAll() {
        return repository.listAll().stream()
                .map(UsuarioResponseDTO::toDTO)
                .toList();
    }

    @Override
    public List<UsuarioResponseDTO> getFuncionarios() {
        return repository.list("cargo", "FUNCIONARIO").stream()
                .map(UsuarioResponseDTO::toDTO)
                .toList();
    }

    @Override
    public List<UsuarioResponseDTO> getNome(String nome) {
        return repository.list("nome", nome).stream()
                .map(UsuarioResponseDTO::toDTO)
                .toList();
    }

    @Override
    public Usuario findByLoginAndSenha(AuthUsuarioDTO auth) {
        return repository.find("login = ?1 and senha = ?2", 
                auth.login(), auth.senha()).firstResult();
    }

    @Override
    public Usuario findByEmailAndSenha(AuthUsuarioDTO auth) {
        return repository.find("email = ?1 and senha = ?2", 
                auth.login(), auth.senha()).firstResult();
    }

    @Override
    public UsuarioResponseDTO getId(Long id) {
        Usuario usuario = repository.findById(id);
        if (usuario == null) {
            throw new NotFoundException();
        }
        return UsuarioResponseDTO.toDTO(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO insert(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setLogin(dto.login());
        usuario.setCargo(dto.cargo());
        repository.persist(usuario);
        return UsuarioResponseDTO.toDTO(usuario);
    }

    @Override
    @Transactional
    public Response insertFuncionario(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setLogin(dto.login());
        usuario.setCargo("FUNCIONARIO");
        repository.persist(usuario);
        return Response.ok(UsuarioResponseDTO.toDTO(usuario)).build();
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        Usuario usuario = repository.findById(id);
        if (usuario == null) {
            throw new NotFoundException();
        }
        usuario.setAtivo(false);
        return Response.ok().build();
    }
}