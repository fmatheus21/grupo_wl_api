package com.firecode.app.model.service;

import com.firecode.app.model.entity.RateEntity;
import com.firecode.app.model.repository.dao.RateDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RateService extends RateDao {

    @Override
    public Page<RateEntity> findAllPaginator(Pageable pageable) {
        return super.findAllPaginator(pageable);
    }

    @Override
    public RateEntity findById(int id) {
        return super.findById(id);
    }

}
