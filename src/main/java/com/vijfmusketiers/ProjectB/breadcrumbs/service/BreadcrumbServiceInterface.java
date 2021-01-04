package com.vijfmusketiers.ProjectB.breadcrumbs.service;

import com.vijfmusketiers.ProjectB.breadcrumbs.Breadcrumbs;

public interface BreadcrumbServiceInterface {

    Breadcrumbs saveBreadcrumbs(Breadcrumbs b);

    void deleteBreadcrumbs(Long routeid);
}
