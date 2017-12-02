package aula1.Controlador;

import aula1.DaoJPA.Persistencia;
import aula1.Modelo.Categoria;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityExistsException;

public class CategoriaController {

    public static void excluir(String codigo) {
        int codigo_ = Integer.parseInt(codigo);

        Persistencia<Categoria> dao = new Persistencia<>(Categoria.class);
        dao.remover(codigo_);
    }

    public static String salvar(String dadosJson) {
        Persistencia<Categoria> dao = new Persistencia<>(Categoria.class);

        Categoria c = Categoria.fromJson(dadosJson);
        JsonObject result = new JsonObject();

        try {
            dao.salvar(c);
            result.addProperty("codigo", c.getCodigo());
        } catch (EntityExistsException e) {
            result.addProperty("mensagem", "Categoria já existe");
        }

        return new Gson().toJson(result);
    }

    public static String recuperar(String codigo) {
        int codigo_ = Integer.parseInt(codigo);

        Persistencia<Categoria> dao = new Persistencia<>(Categoria.class);
        Categoria c = dao.recuperar(codigo_);

        if (c != null) {
            return c.toJson();
        } else {
            return null;
        }
    }

    public static List<String> recuperarTodos() {
        Persistencia<Categoria> dao = new Persistencia<>(Categoria.class);
        List<String> lst = new ArrayList<>();
        
        for (Categoria c : dao.recuperarTodos()) {
            lst.add(c.toJson());
        }
        
        return lst;
    }

    public static String categoriasCodigo() {
        Persistencia<Categoria> dao = new Persistencia<>(Categoria.class);
        List<Categoria> categorias = dao.recuperarTodos();
        JsonObject map = new JsonObject();

        for (Categoria c : categorias) {
            map.addProperty(c.getDescricao(), c.getCodigo());
        }

        return map.toString();
    }

}
