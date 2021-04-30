package org.post.office.mailing.repository.customized;

import lombok.extern.slf4j.Slf4j;
import org.post.office.mailing.model.entity.PostalOfficeEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Repository
public class CustomizedPostalOfficeRepositoryImpl implements CustomizedPostalOfficeRepository<PostalOfficeEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PostalOfficeEntity> findAllWhereDeletedFalse() {
        return entityManager.createQuery(
                "from PostalOfficeEntity where deleted = false",
                PostalOfficeEntity.class)
                .getResultList();
    }

    @Override
    public List<PostalOfficeEntity> findAllWhereDeletedTrue() {
        return entityManager.createQuery(
                "from PostalOfficeEntity where deleted = true",
                PostalOfficeEntity.class)
                .getResultList();
    }

    @Override
    public ResponseEntity<?> findByIdWhereDeletedFalse(String postalCode) {
        try {
            return ResponseEntity.ok().body(entityManager.createQuery(
                    "from PostalOfficeEntity where postalCode = : postal_code and deleted = false",
                    PostalOfficeEntity.class)
                    .setParameter("postal_code", postalCode)
                    .getSingleResult());
        } catch (NoResultException exception) {
            return ResponseEntity.status(400).build();
        }

    }

    @Transactional
    @Override
    public void delete(PostalOfficeEntity postalOfficeEntity) {
        postalOfficeEntity.setDeleted(true);
        entityManager.persist(postalOfficeEntity);
    }
}
