package com.vijfmusketiers.ProjectB.rollator.repository;

import com.vijfmusketiers.ProjectB.rollator.Rollators;
import com.vijfmusketiers.ProjectB.route.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface RollatorRepository extends JpaRepository<Rollators, Long> {

    Rollators getById(long rollatorid);

    List<Rollators> getAllByIdNotNull();

    @Transactional
    Rollators findDistinctById(Long rollatorid);

    @Transactional
    Boolean deleteDistinctById(Long rollatorId);
}
