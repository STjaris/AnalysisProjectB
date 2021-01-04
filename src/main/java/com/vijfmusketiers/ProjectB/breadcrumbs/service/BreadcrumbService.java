package com.vijfmusketiers.ProjectB.breadcrumbs.service;

import com.vijfmusketiers.ProjectB.breadcrumbs.Breadcrumbs;
import com.vijfmusketiers.ProjectB.breadcrumbs.repository.BreadcrumbRepository;
import com.vijfmusketiers.ProjectB.route.service.RouteServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BreadcrumbService implements BreadcrumbServiceInterface{

    @Autowired
    private BreadcrumbRepository breadcrumbRepository;

    @Autowired
    private RouteServiceInterface routeServiceInterface;

    @Override
    public Breadcrumbs saveBreadcrumbs(Breadcrumbs b) {
        return breadcrumbRepository.save(b);
    }

    @Override
    public void deleteBreadcrumbs(Long routeid) {
        breadcrumbRepository.deleteDistinctByRoute(routeServiceInterface.getRoute(routeid));
    }
}
