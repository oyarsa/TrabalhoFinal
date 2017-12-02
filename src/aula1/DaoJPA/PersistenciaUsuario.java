package aula1.DaoJPA;

import aula1.Modelo.Usuario;
import java.util.List;

public class PersistenciaUsuario extends Persistencia<Usuario> {

    public PersistenciaUsuario() {
        super(Usuario.class);
    }

    public Usuario recuperarPorLogin(String login) {
        String[] campos = { "login" };
        Object[] objetos = { login };
        List<Usuario> result = Funcoes.selecionar(Usuario.class, campos, objetos);
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

}
