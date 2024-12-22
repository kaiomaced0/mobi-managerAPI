package br.giraffus.enums.Imovel;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UsoImovel {

        MORADIA(0, "Moradia"),
        COMERCIO(1, "Comercio"),
        INDUSTRIA(2, "Industria"),
        OUTRO(3, "Outro");


    private int id;
    private String label;

    UsoImovel(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static UsoImovel valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (UsoImovel usoImovel : UsoImovel.values()) {
            if (id.equals(usoImovel.getId()))
                return usoImovel;
        }
        throw new IllegalArgumentException("Id inválido:" + id);
    }
}
