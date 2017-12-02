package aula1.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {

    private static final String STR_DRIVER = "org.gjt.mm.mysql.Driver";  // definição de qual banco será utilizado
    private static final String DATABASE = "aluno"; // Nome do banco de dados         
    private static final String IP = "127.0.0.1";  // ip de conexao
    private static final String STR_CON = "jdbc:mysql://" + IP + ":3306/" + DATABASE; // string de conexao com o banco de dados
    private static final String USER = "root"; // Nome do usuário
    private static final String PASSWORD = "123456"; // senha
    private static Connection objConexao = null;

    public static Connection novaConexao() throws SQLException {
        Connection conn = null;
        try {
            Class.forName(STR_DRIVER);
            conn = DriverManager.getConnection(STR_CON, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver nao encontrado: " + e.getMessage());
            throw new RuntimeException("Driver nao encontrado");
        } catch (SQLException e) {
            System.out.println("Erro ao obter a conexao: " + e.getMessage());
            throw e;
        }
        return conn;
    }

    public static Connection geraConexao() throws SQLException {
        if (objConexao == null) {
            objConexao = novaConexao();
        }
        return objConexao;
    }

    private FabricaConexao() {
    }
}
