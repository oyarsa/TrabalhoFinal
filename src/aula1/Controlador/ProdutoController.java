package aula1.Controlador;

import aula1.DaoJPA.Persistencia;
import aula1.Modelo.Produto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityExistsException;

public class ProdutoController {

    public static String salvar(String dadosJson) {
        Persistencia<Produto> dao = new Persistencia<>(Produto.class);

        Produto p = Produto.fromJson(dadosJson);
        JsonObject result = new JsonObject();

        try {
            dao.salvar(p);
            result.addProperty("codigo", p.getCodigo());
        } catch (EntityExistsException e) {
            result.addProperty("mensagem", "Categoria já existe");
        }

        return new Gson().toJson(result);
    }

    public static void excluir(String codigo) {
        int codigo_ = Integer.parseInt(codigo);

        Persistencia<Produto> dao = new Persistencia<>(Produto.class);
        dao.remover(codigo_);
    }

    public static String recuperar(String codigo) {
        int codigo_ = Integer.parseInt(codigo);

        Persistencia<Produto> dao = new Persistencia<>(Produto.class);
        Produto p = dao.recuperar(codigo_);

        if (p != null) {
            return p.toJson();
        } else {
            return null;
        }
    }

    public static List<String> recuperarTodos() {
        Persistencia<Produto> dao = new Persistencia<>(Produto.class);
        List<String> lst = new ArrayList<>();

        for (Produto p : dao.recuperarTodos()) {
            lst.add(p.toJson());
        }

        return lst;
    }

    public static String produtoToDetalhes() {
        Persistencia<Produto> dao = new Persistencia<>(Produto.class);
        List<Produto> produtos = dao.recuperarTodos();
        JsonObject map = new JsonObject();

        for (Produto p : produtos) {
            JsonObject detalhes = new JsonObject();
            detalhes.addProperty("codigo", p.getCodigo());
            detalhes.addProperty("preco", p.getPreco());
            map.add(p.getNome(), detalhes);
        }

        return map.toString();
    }

    public static String codigoToProduto() {
        Persistencia<Produto> dao = new Persistencia<>(Produto.class);
        List<Produto> produtos = dao.recuperarTodos();
        JsonObject map = new JsonObject();

        for (Produto p : produtos) {
            map.addProperty(String.valueOf(p.getCodigo()), p.getNome());
        }

        return map.toString();
    }

}
