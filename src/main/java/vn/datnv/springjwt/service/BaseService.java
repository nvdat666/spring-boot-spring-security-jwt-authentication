package vn.datnv.springjwt.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import vn.datnv.springjwt.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<E extends BaseEntity, ID extends Serializable> {
    E createNew(E entity);

    E update(E entity);

    void physicalDelete(ID id);

    void delete(E entity);

    Optional<E> findById(ID id);

    List<E> findAll();

    Page<E> findAll(@Nullable Specification<E> spec, Pageable page);


}
