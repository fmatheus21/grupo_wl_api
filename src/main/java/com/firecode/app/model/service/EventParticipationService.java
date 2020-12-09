package com.firecode.app.model.service;

import com.firecode.app.model.entity.EventParticipationEntity;
import com.firecode.app.model.entity.EmployeeEntity;
import com.firecode.app.model.entity.EventEntity;
import com.firecode.app.model.repository.dao.EventParticipationDao;
import com.firecode.app.model.repository.filter.RepositoryFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventParticipationService {

    @Autowired
    private EventParticipationDao dao;

    public EventParticipationEntity create(EventParticipationEntity t) {
        return dao.create(t);
    }

    public void delete(EventParticipationEntity t) {
        dao.delete(t);
    }

    public void deleteById(int id) {
        dao.deleteById(id);
    }

    public Page<EventParticipationEntity> findAllPaginator(RepositoryFilter filter, Pageable pageable) {
        return dao.findAllPaginator(filter, pageable);
    }

    public EventParticipationEntity findById(int id) {
        return dao.findById(id);
    }

    public EventParticipationEntity findByIdEmployee(EmployeeEntity idEmployee) {
        return dao.findByIdEmployee(idEmployee);
    }

    public EventParticipationEntity findByIdEventAndIdEmployee(EventEntity idEvent, EmployeeEntity idEmployee) {
        return dao.findByIdEventAndIdEmployee(idEvent, idEmployee);
    }

}
