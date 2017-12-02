package aula1.Modelo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class Pedido extends ModeloBase {
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCriacao;
    private String descricao;
    private double valorTotal;
    
    @OneToMany(mappedBy = "pedido")
    private Set<ProdutoPedido> produtos;
    
    @ManyToOne
    private Usuario usuario;

    public Pedido() {
        this.produtos = new HashSet<>();
    }

    public Pedido(Date data, String descricao, double valorTotal, int codigo) {
        super(codigo);
        this.produtos = new HashSet<>();
        this.dataCriacao = data;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Set<ProdutoPedido> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<ProdutoPedido> produtos) {
        this.produtos = produtos;
    }
    
    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public static Pedido fromJson(String json) {
        JsonObject obj = new Gson().fromJson(json, JsonObject.class);
        Pedido p = new Pedido();
        
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date data;
        try {
            data = df.parse(obj.get("dataCriacao").getAsString());
        } catch (ParseException ex) {
            System.err.println(ex);
            data = new Date();
        }
        
        p.setCodigo(obj.get("codigo").getAsInt());
        p.setDescricao(obj.get("descricao").getAsString());
        p.setValorTotal(obj.get("valorTotal").getAsDouble());
        p.setDataCriacao(data);
        
        return p;
    }

    @Override
    public String toJson() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String data = df.format(dataCriacao);
        
        JsonObject json = new JsonObject();
        json.addProperty("codigo", getCodigo());
        json.addProperty("descricao", descricao);
        json.addProperty("valorTotal", valorTotal);
        json.addProperty("dataCriacao", data);
        json.addProperty("codigoUsuario", usuario.getCodigo());
        
        return json.toString();
    }
    
}
