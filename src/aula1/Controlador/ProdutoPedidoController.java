package aula1.Controlador;

import aula1.DaoJPA.Persistencia;
import aula1.Modelo.Produto;
import aula1.Modelo.ProdutoPedido;
import aula1.Modelo.Pedido;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class ProdutoPedidoController {

    public static String recuperar(String codigo) {
        int codigo_ = Integer.parseInt(codigo);

        Persistencia<ProdutoPedido> dao = new Persistencia<>(ProdutoPedido.class);
        ProdutoPedido p = dao.recuperar(codigo_);

        if (p != null) {
            return p.toJson();
        } else {
            return null;
        }
    }

    public static void excluir(String codigo) {
        int codigo_ = Integer.parseInt(codigo);

        Persistencia<ProdutoPedido> dao = new Persistencia<>(ProdutoPedido.class);
        dao.remover(codigo_);
    }

    public static String salvar(String dadosJson) {
        Persistencia<ProdutoPedido> dao = new Persistencia<>(ProdutoPedido.class);
        ProdutoPedido prodPedido = ProdutoPedido.fromJson(dadosJson);
        JsonObject dados = new Gson().fromJson(dadosJson, JsonObject.class);

        Persistencia<Produto> daoProd = new Persistencia<>(Produto.class);
        int codigoProd = dados.get("codigoProduto").getAsInt();
        Produto prod = daoProd.recuperar(codigoProd);
        prodPedido.setProduto(prod);

        Persistencia<Pedido> daoPedido = new Persistencia<>(Pedido.class);
        int codigoPedido = dados.get("codigoPedido").getAsInt();
        Pedido pedido = daoPedido.recuperar(codigoPedido);
        prodPedido.setPedido(pedido);

        JsonObject json = new JsonObject();
        dao.salvar(prodPedido);
        json.addProperty("codigo", prodPedido.getCodigo());

        return json.toString();
    }

    public static List<String> recuperarTodos(String codigoPedido) {
        int codigo = Integer.parseInt(codigoPedido);
        Persistencia<ProdutoPedido> dao = new Persistencia<>(ProdutoPedido.class);
        List<String> lst = new ArrayList<>();

        for (ProdutoPedido p : dao.recuperarTodos()) {
            if (p.getPedido().getCodigo() == codigo) {
                lst.add(p.toJson());
            }
        }

        return lst;
    }

}
