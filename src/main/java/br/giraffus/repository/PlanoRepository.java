package br.giraffus.repository;

import br.giraffus.model.Plano;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlanoRepository implements PanacheRepository<Plano> {}