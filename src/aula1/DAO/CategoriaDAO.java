package aula1.DAO;

import aula1.Modelo.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaDAO extends Persistencia<Categoria> {

    public CategoriaDAO() {
        super("categoria", "codigo", new String[]{"descricao"});
    }

    @Override
    protected void preparaParametrosEntrada(PreparedStatement stmt, Categoria obj) throws SQLException {
        stmt.setString(1, obj.getDescricao());
    }

    @Override
    protected Categoria recuperaObjeto(ResultSet result) throws SQLException {
        int id = result.getInt("codigo");
        String descricao = result.getString("descricao");
        return new Categoria(id, descricao);
    }
    
}
