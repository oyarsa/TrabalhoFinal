package aula1.DaoJPA;

import aula1.Modelo.ModeloBase;
import java.util.List;

public class Persistencia<T extends ModeloBase> {
    private final Class<T> classePersistencia;

    public Persistencia(Class<T> c) {
        this.classePersistencia = c;
    }
    
    public void salvar(T obj) {
        if (obj.getCodigo() > 0) {
            Funcoes.atualizar(obj);
        } else {
            Funcoes.persistir(obj);
        }
    }
    
    public void remover(int chave) {
        Funcoes.excluir(chave, classePersistencia);
    }
    
    public T recuperar(int chave) {
        return Funcoes.recuperar(chave, classePersistencia);
    }
    
    public List<T> recuperarTodos() {
        return Funcoes.selecionar(classePersistencia, null, null);
    }
    
}
