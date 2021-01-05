package com.vijfmusketiers.ProjectB.breadcrumbs.repository;

import com.vijfmusketiers.ProjectB.breadcrumbs.Breadcrumbs;
import com.vijfmusketiers.ProjectB.route.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BreadcrumbRepository extends JpaRepository<Breadcrumbs, Long> {

    @Transactional
    void deleteDistinctByRoute(Route routeid);


}
