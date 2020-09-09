package dao;

import model.AccountingEntity;
import services.EntityManagerFactorySingleton;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DaoAccountingEntity implements Dao<AccountingEntity> {

    private EntityManager getEntityManager() {
        return EntityManagerFactorySingleton.getInstance().createEntityManager();
    }

    @Override
    public AccountingEntity get(int id) {
        EntityManager entityManager = getEntityManager();
        AccountingEntity accountingEntity = entityManager.find(AccountingEntity.class, id);
        entityManager.close();
        return accountingEntity;
    }

    @Override
    public List<AccountingEntity> getAll() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("FROM ACCOUNTING");
        List resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    public void saveList(List<AccountingEntity> accountingEntityList) {
        EntityManager entityManager = getEntityManager();
        for (AccountingEntity accountingEntity : accountingEntityList) {
            Dao.executeInsideTransaction(entityManager, em -> em.persist(accountingEntity));
        }
        entityManager.close();
    }

    @Override
    public void save(AccountingEntity accountingEntity) {
        EntityManager entityManager = getEntityManager();
        Dao.executeInsideTransaction(entityManager, em -> em.persist(accountingEntity));
        entityManager.close();
    }

    @Override
    public void update(AccountingEntity accountingEntity) {
        EntityManager entityManager = getEntityManager();
        Dao.executeInsideTransaction(entityManager, em -> em.merge(accountingEntity));
        entityManager.close();
    }

    @Override
    public void delete(AccountingEntity accountingEntity) {
        EntityManager entityManager = getEntityManager();
        Dao.executeInsideTransaction(entityManager, em -> em.remove(em.contains(accountingEntity)
                ? accountingEntity : em.merge(accountingEntity)));
        entityManager.close();
    }

}
