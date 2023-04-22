package vn.datnv.springjwt.repository;

import org.springframework.stereotype.Repository;
import vn.datnv.springjwt.entity.User;

import java.util.Optional;


@Repository
public interface UserRepository extends BaseRepository<User, String> {
    Optional<User> findByUsernameAndDeletedFalse(String username);
    Optional<User> findByIdAndDeletedFalse(Long userId);
    Boolean existsByUsernameAndDeletedFalse(String username);

    Boolean existsByEmailAndDeletedFalse(String email);
}
