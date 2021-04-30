package org.post.office.mailing.repository.customized;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomizedPostalOfficeRepository<T> {
    List<T> findAllWhereDeletedFalse();
    List<T> findAllWhereDeletedTrue();
    ResponseEntity<?> findByIdWhereDeletedFalse(String id);
    void delete(T entity);
}
