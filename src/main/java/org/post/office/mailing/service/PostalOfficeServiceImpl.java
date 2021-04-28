package org.post.office.mailing.service;

import org.post.office.mailing.model.entity.PostalOfficeEntity;
import org.post.office.mailing.repository.RepositoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostalOfficeServiceImpl implements PostalOfficeService {
    private final RepositoryRepository postalOfficeRepository;

    public PostalOfficeServiceImpl(RepositoryRepository postalOfficeRepository) {
        this.postalOfficeRepository = postalOfficeRepository;
    }

    @Override
    public void createPostalOffice(PostalOfficeEntity postalOfficeEntity) {
        postalOfficeRepository.save(postalOfficeEntity);
    }

    @Override
    public void editPostalOffice(PostalOfficeEntity postalOfficeEntity) {
        postalOfficeRepository.save(postalOfficeEntity);
    }

    @Override
    public PostalOfficeEntity findPostalOfficeByPostalCode(String postalCode) {
        return postalOfficeRepository.findByIdWhereDeletedFalse(postalCode);
    }

    @Override
    public List<PostalOfficeEntity> findAllPostalOffice() {
        return postalOfficeRepository.findAllWhereDeletedFalse();
    }

    @Override
    public List<PostalOfficeEntity> findAllPostalOfficeWhereStatusDeleted() {
        return postalOfficeRepository.findAllWhereDeletedTrue();
    }

    @Override
    public void deletePostalOfficeByPostalCode(String postalCode) {
        PostalOfficeEntity postalOfficeEntity = postalOfficeRepository.findByIdWhereDeletedFalse(postalCode);
        postalOfficeRepository.delete(postalOfficeEntity);
    }

    @Override
    public long getCountPostalOffices() {
        return postalOfficeRepository.count();
    }

    @Override
    public Optional<?> findPostalOfficeByPostalCodeAndName(String postalCode, String name) {
        return Optional.ofNullable(postalOfficeRepository.findByPostalCodeAndName(postalCode, name)).orElseThrow(null);
    }
}
