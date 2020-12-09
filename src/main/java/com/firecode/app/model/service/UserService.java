package com.firecode.app.model.service;

import com.firecode.app.model.entity.UserEntity;
import com.firecode.app.model.repository.dao.UserDao;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService extends UserDao {

    @Override
    public Page<UserEntity> findAllPaginator(Pageable pageable) {
        return super.findAllPaginator(pageable);
    }

    @Override
    public UserEntity findById(int id) {
        return super.findById(id);
    }

    @Override
    public Optional<UserEntity> findByUsername(String value) {
        return super.findByUsername(value);
    }

}
