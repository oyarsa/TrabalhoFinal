package aula1.Modelo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ProdutoPedido extends ModeloBase {

    public static ProdutoPedido fromJson(String dadosJson) {
        ProdutoPedido p = new ProdutoPedido();
        JsonObject obj = new Gson().fromJson(dadosJson, JsonObject.class);

        p.setCodigo(obj.get("codigo").getAsInt());
        p.setQuantidade(obj.get("quantidade").getAsInt());
        p.setValor(obj.get("valor").getAsDouble());
        p.setValorTotal(obj.get("valorTotal").getAsDouble());

        return p;
    }

    @Override
    public String toJson() {
        JsonObject json = new JsonObject();
        
        json.addProperty("codigo", getCodigo());
        json.addProperty("codigoProduto", produto.getCodigo());
        json.addProperty("codigoPedido", pedido.getCodigo());
        json.addProperty("quantidade", quantidade);
        json.addProperty("valor", valor);
        json.addProperty("valorTotal", valorTotal);
        
        return json.toString();
    }
    
    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Pedido pedido;

    private int quantidade;
    private double valor;
    private double valorTotal;

    public ProdutoPedido() {
    }

    public ProdutoPedido(Produto produto, Pedido pedido, int quantidade, double valor, double valorTotal) {
        this.produto = produto;
        this.pedido = pedido;
        this.quantidade = quantidade;
        this.valor = valor;
        this.valorTotal = valorTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
