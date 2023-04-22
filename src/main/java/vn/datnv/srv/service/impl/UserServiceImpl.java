package vn.datnv.srv.service.impl;


import org.springframework.stereotype.Service;
import vn.datnv.srv.entity.User;
import vn.datnv.srv.repository.UserRepository;
import vn.datnv.srv.service.UserService;

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
