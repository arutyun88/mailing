package org.post.office.mailing.repository.customized;

import org.post.office.mailing.model.entity.PostalOfficeEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class CustomizedRepositoryImpl implements CustomizedRepository<PostalOfficeEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PostalOfficeEntity> findAllWhereDeletedFalse() {
        return entityManager.createQuery(
                "from PostalOffice where deleted = false",
                PostalOfficeEntity.class)
                .getResultList();
    }

    @Override
    public List<PostalOfficeEntity> findAllWhereDeletedTrue() {
        return entityManager.createQuery(
                "from PostalOffice where deleted = true",
                PostalOfficeEntity.class)
                .getResultList();
    }

    @Override
    public PostalOfficeEntity findByIdWhereDeletedFalse(String postalCode) {
        return entityManager.createQuery(
                "from PostalOffice where postalCode = postal_code and deleted = false",
                PostalOfficeEntity.class)
                .getSingleResult();
    }

    @Transactional
    @Override
    public void delete(PostalOfficeEntity entity) {
        entity.setDeleted(true);
        entityManager.persist(entity);
    }
}
