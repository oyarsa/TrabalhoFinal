package aula1.DAO;

import aula1.Misc.Mensagens;
import aula1.Modelo.ModeloBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Persistencia<T extends ModeloBase> {

    private final String tabela;
    private final String chave;
    private final String[] campos;

    private String sqlInsert;
    private String sqlUpdate;
    private String sqlDelete;
    private String sqlSelect;
    private String sqlSelectAll;

    protected Persistencia(String tabela, String chave, String[] campos) {
        this.tabela = tabela;
        this.chave = chave;
        this.campos = campos;
        formaSql();
    }

    private void formaSql() {
        formaSqlInsert();
        formaSqlUpdate();
        formaSqlDelete();
        formaSqlSelect();
        formaSqlSelectAll();
    }

    protected void formaSqlInsert() {
        StringBuilder sb = new StringBuilder("INSERT INTO " + tabela);

        sb.append(" (");
        for (int i = 0; i < campos.length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(campos[i]);
        }

        sb.append(") VALUES (");
        for (int i = 0; i < campos.length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append("?");
        }
        sb.append(")");

        sqlInsert = sb.toString();
    }

    protected void formaSqlUpdate() {
        StringBuilder sb = new StringBuilder("UPDATE " + tabela);

        sb.append(" SET ");
        for (int i = 0; i < campos.length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(campos[i]).append(" = ?");
        }

        sb.append(" WHERE ").append(chave).append(" = ?");

        sqlUpdate = sb.toString();
    }

    protected void formaSqlDelete() {
        sqlDelete = String.format("DELETE FROM %s WHERE %s = ?", tabela, chave);
    }

    protected void formaSqlSelect() {
        sqlSelect = String.format("SELECT * FROM %s WHERE %s = ?", tabela, chave);
    }

    public void print() {
        formaSql();

        System.out.println(sqlInsert);
        System.out.println(sqlUpdate);
        System.out.println(sqlDelete);
        System.out.println(sqlSelect);
    }

    public String executarSql(String sql) {
        Connection conn;
        try {
            conn = FabricaConexao.geraConexao();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return Mensagens.SUCESSO;
        } catch (SQLException ex) {
            return Mensagens.ERRO_DB + ex.getMessage();
        }

    }

    public String salvar(T obj) {
        Connection conn;
        boolean isUpdate = obj.getCodigo() != 0;
        String sql = isUpdate ? sqlUpdate : sqlInsert;

        try {
            conn = FabricaConexao.geraConexao();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparaParametrosEntrada(stmt, obj);

            if (isUpdate) {
                stmt.setInt(campos.length + 1, obj.getCodigo());
            }

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (!isUpdate && rs.next()) {
                obj.setCodigo(rs.getInt(1));
            }

            return Mensagens.SUCESSO;
        } catch (SQLException ex) {
            return Mensagens.ERRO_DB + ex.getMessage();
        }
    }

    protected abstract void preparaParametrosEntrada(
            PreparedStatement stmt, T obj) throws SQLException;

    public String excluir(int codigo) {
        try {
            Connection conn = FabricaConexao.geraConexao();
            PreparedStatement stmt = conn.prepareStatement(sqlDelete);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
            return Mensagens.SUCESSO;
        } catch (SQLException ex) {
            return Mensagens.ERRO_DB + ex.getMessage();
        }
    }

    public T recuperar(int codigo) {
        try {
            Connection conn = FabricaConexao.geraConexao();
            PreparedStatement stmt = conn.prepareStatement(sqlSelect);
            stmt.setInt(1, codigo);
            ResultSet result = stmt.executeQuery();
            return result.next() ? recuperaObjeto(result) : null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    protected abstract T recuperaObjeto(ResultSet result) throws SQLException;

    private void formaSqlSelectAll() {
        sqlSelectAll = String.format("SELECT * FROM %s", tabela);
    }

    public List<T> recuperarTodos() {
        List<T> ts = new ArrayList<>();

        try {
            Connection conn = FabricaConexao.geraConexao();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlSelectAll);
            
            while (rs.next()) {
                ts.add(recuperaObjeto(rs));
            }
            
            return ts;
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
            return null;
        }
    }
}
