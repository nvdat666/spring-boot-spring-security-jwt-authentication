package vn.datnv.srv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import vn.datnv.srv.entity.BaseEntity;
import vn.datnv.srv.repository.BaseRepository;
import vn.datnv.srv.service.BaseService;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<E extends BaseEntity, ID extends Serializable, R extends BaseRepository<E, ID>>
    implements BaseService<E, ID> {

    @Autowired
    protected R repository;

    @Override
    public E createNew(E entity) {
        entity.setDeleted(false);
        return repository.save(entity);
    }

    @Override
    public E update(E entity) {
        return repository.save(entity);
    }

    @Override
    public void physicalDelete(ID id) {
        repository.deleteById(id);
    }


    @Override
    public void delete(E entity) {
        entity.setDeleted(true);
        repository.save(entity);
    }

    @Override
    public Optional<E> findById(ID id) {
        return repository.findByIdAndDeletedFalse(id);
    }

    @Override
    public List<E> findAll() {
        return repository.findAllByDeletedFalse();
    }

    @Override
    public Page<E> findAll(Specification<E> spec, Pageable page) {
        return repository.findAll(spec, page);
    }




}
