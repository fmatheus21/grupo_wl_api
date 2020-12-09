package com.firecode.app.model.repository.dao;

import com.firecode.app.model.entity.EventParticipationEntity;
import com.firecode.app.model.entity.EmployeeEntity;
import com.firecode.app.model.entity.EventEntity;
import com.firecode.app.model.repository.filter.RepositoryFilter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.firecode.app.model.repository.EventParticipationRepository;
import org.springframework.stereotype.Component;

@Component
public class EventParticipationDao implements GenericDao<EventParticipationEntity> {

    @Autowired
    private EventParticipationRepository repository;

    @Override
    public EventParticipationEntity findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<EventParticipationEntity> findAll(String orderBy) {
        return repository.findAll(Sort.by(Sort.Order.asc(orderBy)));
    }

    @Override
    public EventParticipationEntity create(EventParticipationEntity t) {
        return repository.save(t);
    }

    @Override
    public EventParticipationEntity update(EventParticipationEntity t) {
        return repository.save(t);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public Page<EventParticipationEntity> findAllPaginator(RepositoryFilter filter, Pageable pageable) {
        return repository.findAllPaginator(filter, pageable);
    }

    public EventParticipationEntity findByIdEmployee(EmployeeEntity idEmployee) {
        return repository.findByIdEmployee(idEmployee);
    }

    public EventParticipationEntity findByIdEventAndIdEmployee(EventEntity idEvent, EmployeeEntity idEmployee) {
        return repository.findByIdEventAndIdEmployee(idEvent, idEmployee);
    }

    /*public Page<EventParticipationEntity> findAllPaginator(int page, int size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "id");
        return repository.findAll(pageRequest);*/
}
