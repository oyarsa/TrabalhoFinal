package aula1.DaoJPA;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class Funcoes {

    public static EntityManager abrirTransacao() {
        EntityManager em = FabricaJPA.getEntityManager();
        em.getTransaction().begin();
        return em;
    }

    public static void fecharTransacao(EntityManager em, boolean commit) {
        if (commit) {
            em.getTransaction().commit();
        } else {
            em.getTransaction().rollback();
        }
        em.close();
    }

    public static void persistir(Object obj) {
        EntityManager em = abrirTransacao();
        em.persist(obj);
        fecharTransacao(em, true);
    }

    public static void atualizar(Object obj) {
        EntityManager em = abrirTransacao();
        em.merge(obj);
        fecharTransacao(em, true);
    }

    public static void excluir(int chave, Class classe) {
        EntityManager em = abrirTransacao();
        Object obj = em.find(classe, chave);
        if (obj != null) {
            em.remove(obj);
        }
        fecharTransacao(em, true);
    }

    public static <T> T recuperar(int chave, Class<T> classe) {
        EntityManager em = FabricaJPA.getEntityManager();
        return em.find(classe, chave);
    }

    public static <T> List<T> selecionar(Class<T> classe, String[] campos, Object[] objetos) {
        if (campos != null && objetos != null && campos.length != objetos.length) {
            return null;
        }

        if ((campos != null && objetos == null) || (campos == null && objetos != null)) {
            return null;
        }

        EntityManager em = FabricaJPA.getEntityManager();
        String sql = String.format("select o from %s o ", classe.getName());

        if (campos != null && campos.length >= 0) {
            sql += " where ";
            for (int i = 0; i < campos.length; i++) {
                sql += String.format(" o.%s = ?%d", campos[i], i+1);
                if (i != campos.length - 1) {
                    sql += " and ";
                }
            }
        }

        TypedQuery<T> query = em.createQuery(sql, classe);

        if (objetos != null) {
            for (int i = 0; i < objetos.length; i++) {
                query.setParameter(i+1, objetos[i]);
            }
        }

        return query.getResultList();
    }
}
