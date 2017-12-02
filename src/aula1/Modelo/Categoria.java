package aula1.Modelo;

import com.google.gson.Gson;
import javax.persistence.Entity;

@Entity
public class Categoria extends ModeloBase {

    private String descricao;

    public Categoria() {
    }

    public Categoria(int codigo, String descricao) {
        super(codigo);
        this.descricao = descricao;
    }

    public Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public static Categoria fromJson(String json) {
        return new Gson().fromJson(json, Categoria.class);
    }
}
