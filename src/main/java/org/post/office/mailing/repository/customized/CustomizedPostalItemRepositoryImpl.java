package org.post.office.mailing.repository.customized;

import lombok.extern.slf4j.Slf4j;
import org.post.office.mailing.model.entity.PostalItemEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Repository
public class CustomizedPostalItemRepositoryImpl implements CustomizedPostalItemRepository<PostalItemEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PostalItemEntity> findAllWhereDeletedFalse() {
        return entityManager.createQuery(
                "from PostalItemEntity where deleted = false",
                PostalItemEntity.class)
                .getResultList();
    }

    @Override
    public List<PostalItemEntity> findAllWhereDeletedTrue() {
        return entityManager.createQuery(
                "from PostalItemEntity where deleted = true",
                PostalItemEntity.class)
                .getResultList();
    }

    @Override
    public ResponseEntity<?> findByIdWhereDeletedFalse(long postalItem) {
        try {
            return ResponseEntity.ok().body(entityManager.createQuery(
                    "from PostalItemEntity where id = : id and deleted = false",
                    PostalItemEntity.class)
                    .setParameter("id", postalItem)
                    .getSingleResult());
        } catch (NoResultException exception) {
            return ResponseEntity.status(400).build();
        }

    }

    @Transactional
    @Override
    public void delete(PostalItemEntity postalItemEntity) {
        postalItemEntity.setDeleted(true);
        entityManager.persist(postalItemEntity);
    }
}
