package aula1.Modelo;

import com.google.gson.Gson;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Produto extends ModeloBase {

    @Column(unique = true)
    private String nome;
    private double preco;

    public Produto(String nome, double preco, int codigo) {
        super(codigo);
        this.nome = nome;
        this.preco = preco;
    }

    public Produto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public static Produto fromJson(String json) {
        return new Gson().fromJson(json, Produto.class);
    }

}
