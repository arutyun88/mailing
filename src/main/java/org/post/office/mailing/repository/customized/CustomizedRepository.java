package org.post.office.mailing.repository.customized;

import java.util.List;

public interface CustomizedRepository<T> {
    List<T> findAllWhereDeletedFalse();
    List<T> findAllWhereDeletedTrue();
    T findByIdWhereDeletedFalse(String id);
    void delete(T entity);
}
