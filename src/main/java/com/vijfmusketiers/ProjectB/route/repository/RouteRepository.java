package com.vijfmusketiers.ProjectB.route.repository;

import com.vijfmusketiers.ProjectB.route.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {


    Route getById(Long routeid);

    @Transactional
    Boolean deleteDistinctById(Long routeid);

    @Transactional
    Route findDistinctById(Long routeid);

    @Transactional
    List<Route> findAllByUserId(Long usersid);

}
