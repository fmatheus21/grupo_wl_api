package com.firecode.app.model.repository;

import com.firecode.app.model.entity.EventParticipationEntity;
import com.firecode.app.model.entity.EmployeeEntity;
import com.firecode.app.model.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.firecode.app.model.repository.query.EventParticipationRepositoryQuery;

@Repository
public interface EventParticipationRepository extends JpaRepository<EventParticipationEntity, Integer>, EventParticipationRepositoryQuery {

    public EventParticipationEntity findByIdEmployee(EmployeeEntity idEmployee);

    public EventParticipationEntity findByIdEventAndIdEmployee(EventEntity idEvent, EmployeeEntity idEmployee);

}
