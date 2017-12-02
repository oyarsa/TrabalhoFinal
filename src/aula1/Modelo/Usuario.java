package aula1.Modelo;

import aula1.Misc.Senha;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Usuario extends ModeloBase {

    private String nome;
    @Column(unique = true)
    private String login;
    private String senhaHash;
    @ManyToOne
    private Categoria categoria;

    public Usuario() {
    }
    
    
    @Override
    public String toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("codigo", getCodigo());
        json.addProperty("nome", nome);
        json.addProperty("login", login);
        json.addProperty("senha", senhaHash);
        json.addProperty("codigoCategoria", categoria.getCodigo());
        return json.toString();
    }

    public Usuario(String nome, String login, String senha, Categoria categoria) {
        this.nome = nome;
        this.login = login;
        this.senhaHash = Senha.hash(senha);
        this.categoria = categoria;
    }

    public Usuario(int codigo, String nome, String login, String senha, Categoria categoria) {
        super(codigo);
        this.nome = nome;
        this.login = login;
        this.senhaHash = Senha.hash(senha);
        this.categoria = categoria;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senhaHash;
    }

    public void setSenha(String senha) {
        this.senhaHash = senha;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "id=" + getCodigo() + ", nome=" + nome + ", login="
                + login + ", senha=" + senhaHash + '}';
    }

    public boolean autenticar(String senha) {
        return Senha.check(senha, this.senhaHash);
    }
    
    
    public static Usuario fromJson(String json) {
        return new Gson().fromJson(json, Usuario.class);
    }
}
