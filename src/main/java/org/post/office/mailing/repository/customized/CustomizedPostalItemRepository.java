package org.post.office.mailing.repository.customized;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomizedPostalItemRepository<T> {
    List<T> findAllWhereDeletedFalse();
    List<T> findAllWhereDeletedTrue();
    ResponseEntity<?> findByIdWhereDeletedFalse(long id);
    void delete(T entity);
}
