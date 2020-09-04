package dao;

import model.AccountingEntity;
import services.EntityManagerFactorySingleton;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.function.Consumer;

public class DaoAccountingEntity implements Dao<AccountingEntity> {

    private EntityManager getEntityManager() {
        return EntityManagerFactorySingleton.getInstance().createEntityManager();
    }

    @Override
    public AccountingEntity get(long id) {
        EntityManager entityManager = getEntityManager();
        return entityManager.find(AccountingEntity.class, id);
    }

    @Override
    public List<AccountingEntity> getAll() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("FROM ACCOUNTING");
        return query.getResultList();
    }

    @Override
    public void save(AccountingEntity accountingEntity) {
        EntityManager entityManager = getEntityManager();
        Dao.executeInsideTransaction(entityManager, em -> em.persist(accountingEntity));

    }

    @Override
    public void update(AccountingEntity accountingEntity) {
        EntityManager entityManager = getEntityManager();
        Dao.executeInsideTransaction(entityManager, em -> em.merge(accountingEntity));
    }

    @Override
    public void delete(AccountingEntity accountingEntity) {
        EntityManager entityManager = getEntityManager();
        Dao.executeInsideTransaction(entityManager, em -> em.remove(accountingEntity));
    }

}
