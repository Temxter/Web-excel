package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.function.Consumer;

public interface Dao<T> {

    T get(long id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);

    static void executeInsideTransaction(EntityManager entityManager, Consumer<EntityManager> action){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            action.accept(entityManager);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }
}
