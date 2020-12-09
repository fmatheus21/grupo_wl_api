package com.firecode.app.model.service;

import com.firecode.app.model.entity.EmployeeEntity;
import com.firecode.app.model.repository.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao dao;

    public Page<EmployeeEntity> findAllPaginator(Pageable pageable) {
        return dao.findAllPaginator(pageable);
    }

    public EmployeeEntity findById(int id) {
        return dao.findById(id);
    }

}
