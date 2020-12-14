package com.firecode.app.model.repository;

import com.firecode.app.model.entity.RateEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<RateEntity, Integer> {

    public List<RateEntity> findByEmployee(boolean b);

}
