package aula1.Controlador;

import aula1.DaoJPA.Persistencia;
import aula1.DaoJPA.PersistenciaUsuario;
import aula1.Modelo.Pedido;
import aula1.Modelo.ProdutoPedido;
import aula1.Modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class PedidoController {

    public static String recuperar(String codigo) {
        int codigo_ = Integer.parseInt(codigo);

        Persistencia<Pedido> dao = new Persistencia<>(Pedido.class);
        Pedido p = dao.recuperar(codigo_);

        if (p != null) {
            return p.toJson();
        } else {
            return null;
        }
    }

    public static void excluir(String codigo) {
        int codigo_ = Integer.parseInt(codigo);

        Persistencia<Pedido> dao = new Persistencia<>(Pedido.class);
        dao.remover(codigo_);
    }

    public static String salvar(String dadosJson) {
        Persistencia<Pedido> dao = new Persistencia<>(Pedido.class);
        Pedido p = Pedido.fromJson(dadosJson);
        JsonObject dados = new Gson().fromJson(dadosJson, JsonObject.class);
        
        PersistenciaUsuario daoCat = new PersistenciaUsuario();
        int codigoUsu = dados.get("codigoUsuario").getAsInt();
        Usuario u = daoCat.recuperar(codigoUsu);
        p.setUsuario(u);

        JsonObject json = new JsonObject();
        dao.salvar(p);
        json.addProperty("codigo", p.getCodigo());

        return json.toString();
    }

    public static List<String> recuperarTodos() {
        Persistencia<Pedido> dao = new Persistencia<>(Pedido.class);
        List<String> lst = new ArrayList<>();

        for (Pedido p : dao.recuperarTodos()) {
            lst.add(p.toJson());
        }

        return lst;
    }

    public static String recalcularValorTotal(String codigo) {
        int codigo_ = Integer.parseInt(codigo);
        Persistencia<ProdutoPedido> dao = new Persistencia<>(ProdutoPedido.class);
        List<ProdutoPedido> produtos = dao.recuperarTodos();
        
        double total = 0;
        for (ProdutoPedido p : produtos) {
            if (p.getPedido().getCodigo() == codigo_)
                total += p.getValorTotal();
        }
        
        JsonObject json = new JsonObject();
        json.addProperty("valorTotal", total);
        return json.toString();
    }

}
