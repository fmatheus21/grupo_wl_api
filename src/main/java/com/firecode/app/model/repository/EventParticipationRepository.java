package com.firecode.app.model.repository;

import com.firecode.app.model.entity.EventParticipationEntity;
import com.firecode.app.model.entity.EmployeeEntity;
import com.firecode.app.model.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.firecode.app.model.repository.query.EventParticipationRepositoryQuery;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public interface EventParticipationRepository extends JpaRepository<EventParticipationEntity, Integer>, EventParticipationRepositoryQuery {

    public EventParticipationEntity findByIdEmployee(EmployeeEntity idEmployee);

    public EventParticipationEntity findByIdEventAndIdEmployee(EventEntity idEvent, EmployeeEntity idEmployee);

   
    @Modifying    
    @Query("delete from EventParticipationEntity e where e.id=:id")
    public void deleteById(@Param("id") int id);

}
