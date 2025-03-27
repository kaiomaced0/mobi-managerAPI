package br.giraffus.repository;

import br.giraffus.model.PagamentoEmpresa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PagamentoEmpresaRepository implements PanacheRepository<PagamentoEmpresa> {}