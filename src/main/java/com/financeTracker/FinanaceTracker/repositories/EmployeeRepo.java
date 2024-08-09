package com.financeTracker.FinanaceTracker.repositories;

import com.financeTracker.FinanaceTracker.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity,Long> {

}
