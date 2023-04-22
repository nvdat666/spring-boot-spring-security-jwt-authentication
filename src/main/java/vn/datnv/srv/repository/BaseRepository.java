package vn.datnv.srv.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.datnv.srv.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity, ID extends Serializable> extends PagingAndSortingRepository<E, ID>
                                                            , JpaSpecificationExecutor<E> {
    Optional<E> findByIdAndDeletedFalse(ID id);
    List<E> findAllByDeletedFalse();


}
