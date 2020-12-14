package com.firecode.app.model.service;

import com.firecode.app.model.entity.RateEntity;
import com.firecode.app.model.repository.dao.RateDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RateService {

    @Autowired
    private RateDao dao;

    public Page<RateEntity> findAllPaginator(Pageable pageable) {
        return dao.findAllPaginator(pageable);
    }

    public RateEntity findById(int id) {
        return dao.findById(id);
    }

    public List<RateEntity> findByEmployee(boolean b) {
        return dao.findByEmployee(b);
    }

}
