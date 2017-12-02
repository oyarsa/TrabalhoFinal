package aula1.Controlador;

import aula1.DaoJPA.Persistencia;
import aula1.DaoJPA.PersistenciaUsuario;
import aula1.Modelo.Categoria;
import aula1.Modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;

public class UsuarioController {

    public static String autenticar(String dadosJson) {
        JsonObject dados = new Gson().fromJson(dadosJson, JsonObject.class);
        String login = dados.get("login").getAsString();
        String senha = dados.get("senha").getAsString();

        PersistenciaUsuario dao = new PersistenciaUsuario();
        Usuario u = dao.recuperarPorLogin(login);

        if (u == null) {
            return "Usuário não encontrado";
        }
        if (!u.autenticar(senha)) {
            return "Senha incorreta";
        }
        return null;
    }

    public static String recuperar(String codigo) {
        int codigo_ = Integer.parseInt(codigo);

        PersistenciaUsuario dao = new PersistenciaUsuario();
        Usuario u = dao.recuperar(codigo_);

        if (u != null) {
            return u.toJson();
        } else {
            return null;
        }
    }

    public static void excluir(String codigo) {
        int codigo_ = Integer.parseInt(codigo);

        PersistenciaUsuario dao = new PersistenciaUsuario();
        dao.remover(codigo_);
    }

    public static List<String> recuperarTodos() {
        PersistenciaUsuario dao = new PersistenciaUsuario();
        List<String> lst = new ArrayList<>();

        for (Usuario u : dao.recuperarTodos()) {
            lst.add(u.toJson());
        }

        return lst;
    }

    public static String salvar(String dadosJson) {
        PersistenciaUsuario dao = new PersistenciaUsuario();
        Usuario u = Usuario.fromJson(dadosJson);
        JsonObject dados = new Gson().fromJson(dadosJson, JsonObject.class);

        Persistencia<Categoria> daoCat = new Persistencia<>(Categoria.class);
        int codigoCat = dados.get("codigoCategoria").getAsInt();
        Categoria cat = daoCat.recuperar(codigoCat);
        u.setCategoria(cat);

        JsonObject json = new JsonObject();
        try {
            dao.salvar(u);
            json.addProperty("codigo", u.getCodigo());
        } catch (EntityExistsException e) {
            json.addProperty("mensagem", "Usuário já existe");
        } catch (PersistenceException e) {
            json.addProperty("mensagem", "Login já existe");
        }

        return json.toString();
    }

    public static String codigoToUsuario() {
        PersistenciaUsuario dao = new PersistenciaUsuario();
        List<Usuario> usuarios = dao.recuperarTodos();
        JsonObject map = new JsonObject();

        for (Usuario u : usuarios) {
            map.addProperty(String.valueOf(u.getCodigo()), u.getNome());
        }

        return map.toString();
    }

    public static String usuarioToCodigo() {
        PersistenciaUsuario dao = new PersistenciaUsuario();
        List<Usuario> usuarios = dao.recuperarTodos();
        JsonObject map = new JsonObject();

        for (Usuario u : usuarios) {
            map.addProperty(u.getNome(), String.valueOf(u.getCodigo()));
        }

        return map.toString();
    }
}
