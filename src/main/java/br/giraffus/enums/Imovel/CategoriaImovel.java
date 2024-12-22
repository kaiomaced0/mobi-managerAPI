package br.giraffus.enums.Imovel;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CategoriaImovel {

        RESIDENCIAL(0, "Residencial"),
        COMERCIAL(1, "Comercial"),
        INDUSTRIAL(2, "Industrial"),
        TERRENO(3, "Terreno");


    private int id;
    private String label;

    CategoriaImovel(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static CategoriaImovel valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (CategoriaImovel categoriaImovel : CategoriaImovel.values()) {
            if (id.equals(categoriaImovel.getId()))
                return categoriaImovel;
        }
        throw new IllegalArgumentException("Id inválido:" + id);
    }
}
