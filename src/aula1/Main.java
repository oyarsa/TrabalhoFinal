package aula1;

import aula1.DaoJPA.Persistencia;
import aula1.Modelo.Categoria;
import aula1.Modelo.Usuario;

public class Main {

    public static void main(String[] args) {
        
        Categoria c = new Categoria("oi");
        Persistencia<Categoria> dao2 = new Persistencia<>(Categoria.class);
        dao2.salvar(c);
        
        Usuario u = new Usuario("Amanda", "id", "id", c);
        Persistencia<Usuario> dao = new Persistencia<>(Usuario.class);
        dao.salvar(u);
    }

}
