package br.giraffus.repository;

import br.giraffus.model.Inquilino;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InquilinoRepository implements PanacheRepository<Inquilino> {
}
