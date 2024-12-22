package br.giraffus.enums.notificacao;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoNotificacao {

    COBRANCA(0, "Cobranca"),
    AVISO(1, "Aviso"),
    LEMBRETE(2, "Lembrete");


    private int id;
    private String label;

    TipoNotificacao(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoNotificacao valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (TipoNotificacao tipoNotificacao : TipoNotificacao.values()) {
            if (id.equals(tipoNotificacao.getId()))
                return tipoNotificacao;
        }
        throw new IllegalArgumentException("Id inválido:" + id);
    }
}
