package vn.datnv.springjwt.service;


import vn.datnv.springjwt.entity.User;

import java.util.Optional;


public interface UserService extends BaseService<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(long id);
    
}
