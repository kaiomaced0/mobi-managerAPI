package br.giraffus.repository;

import br.giraffus.model.Proprietario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProprietarioRepository implements PanacheRepository<Proprietario> {
}
