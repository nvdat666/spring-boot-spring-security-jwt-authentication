package vn.datnv.springjwt.service.impl;


import org.springframework.stereotype.Service;
import vn.datnv.springjwt.entity.User;
import vn.datnv.springjwt.repository.UserRepository;
import vn.datnv.springjwt.service.UserService;

import java.util.Optional;


@Service
public class UserServiceImpl extends BaseServiceImpl<User, String, UserRepository>
        implements UserService {
    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsernameAndDeletedFalse(username);
    }

    @Override
    public Optional<User> findById(long id) {
        return repository.findByIdAndDeletedFalse(id);
    }

}
