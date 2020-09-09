package dao;


import model.PeriodEntity;
import services.EntityManagerFactorySingleton;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DaoPeriodEntity implements Dao<PeriodEntity> {

    private EntityManager getEntityManager() {
        return EntityManagerFactorySingleton.getInstance().createEntityManager();
    }

    @Override
    public PeriodEntity get(int id) {
        EntityManager entityManager = getEntityManager();
        PeriodEntity periodEntity = entityManager.find(PeriodEntity.class, id);
        entityManager.close();
        return periodEntity;
    }

    @Override
    public List<PeriodEntity> getAll() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("FROM PeriodEntity");
        List resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    @Override
    public void save(PeriodEntity periodEntity) {
        EntityManager entityManager = getEntityManager();
        Dao.executeInsideTransaction(entityManager, em -> em.persist(periodEntity));
        entityManager.close();
    }

    @Override
    public void update(PeriodEntity periodEntity) {
        EntityManager entityManager = getEntityManager();
        Dao.executeInsideTransaction(entityManager, em -> em.merge(periodEntity));
        entityManager.close();
    }

    @Override
    public void delete(PeriodEntity periodEntity) {
        EntityManager entityManager = getEntityManager();
        Dao.executeInsideTransaction(entityManager, em -> em.remove(periodEntity));
        entityManager.close();
    }

}
