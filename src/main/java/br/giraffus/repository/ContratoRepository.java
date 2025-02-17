package br.giraffus.repository;

import br.giraffus.model.Contrato;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContratoRepository implements PanacheRepository<Contrato> {

}
