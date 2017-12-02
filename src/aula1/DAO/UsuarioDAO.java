package aula1.DAO;

import aula1.Modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO extends Persistencia<Usuario> {

    public UsuarioDAO() {
        super("usuario", "Id", new String[]{"Nome", "Login", "Senha"});
    }

    @Override
    protected void preparaParametrosEntrada(PreparedStatement stmt, Usuario usr) throws SQLException {
        stmt.setString(1, usr.getNome());
        stmt.setString(2, usr.getLogin());
        stmt.setString(3, usr.getSenha());
    }

    @Override
    protected Usuario recuperaObjeto(ResultSet result) throws SQLException {
        String nome  = result.getString("Nome");
        String login = result.getString("Login");
        String senha = result.getString("Senha");
        int id = result.getInt("Id");
        return new Usuario(id, nome, login, senha, null);
    }

}
