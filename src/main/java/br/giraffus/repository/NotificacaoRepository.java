package br.giraffus.repository;

import br.giraffus.model.Notificacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotificacaoRepository implements PanacheRepository<Notificacao> {}