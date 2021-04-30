package org.post.office.mailing.service;

import lombok.extern.slf4j.Slf4j;
import org.post.office.mailing.model.entity.PostalOfficeEntity;
import org.post.office.mailing.repository.PostalOfficeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class PostalOfficeServiceImpl implements PostalOfficeService {
    private final PostalOfficeRepository postalOfficeRepository;

    public PostalOfficeServiceImpl(PostalOfficeRepository postalOfficeRepository) {
        this.postalOfficeRepository = postalOfficeRepository;
    }

    @Override
    public void createPostalOffice(PostalOfficeEntity postalOfficeEntity) {
        ResponseEntity<?> responseEntity = findPostalOfficeByPostalCode(postalOfficeEntity.getPostalCode());
        if (responseEntity.getStatusCode().value() != 200) {
            postalOfficeRepository.save(postalOfficeEntity);
            log.info("Created Postal Office: " + postalOfficeEntity.getPostalCode());
        } else {
            log.warn("Postal Office " + postalOfficeEntity.getPostalCode() + " already created!");
        }
    }

    @Override
    public void editPostalOffice(PostalOfficeEntity postalOfficeEntity) {
        postalOfficeRepository.save(postalOfficeEntity);
    }

    @Override
    public ResponseEntity<?> findPostalOfficeByPostalCode(String postalCode) {
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

//    todo
    @Override
    public void deletePostalOfficeByPostalCode(String postalCode) {
        ResponseEntity<?> postalOfficeEntity = postalOfficeRepository.findByIdWhereDeletedFalse(postalCode);
        if (postalOfficeEntity.getStatusCode().is2xxSuccessful()) {
            postalOfficeRepository.delete((PostalOfficeEntity) Objects.requireNonNull(postalOfficeEntity.getBody()));
            log.info("Postal Office " + postalCode + " deleted");
        }
    }

    @Override
    public long getCountPostalOffices() {
        return postalOfficeRepository.count();
    }
}
