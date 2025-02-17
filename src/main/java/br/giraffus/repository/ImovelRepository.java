package br.giraffus.repository;

import br.giraffus.model.Imovel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ImovelRepository implements PanacheRepository<Imovel> {
}
