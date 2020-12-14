package com.firecode.app.model.repository;

import com.firecode.app.model.entity.EventGuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface EventGuestRepository extends JpaRepository<EventGuestEntity, Integer> {

    @Modifying
    @Query("delete from EventGuestEntity e where e.id=:id")
    public void deleteById(@Param("id") int id);
    
    public EventGuestEntity findByDocument(String value);
    

}
