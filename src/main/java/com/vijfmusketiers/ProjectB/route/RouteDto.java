package com.vijfmusketiers.ProjectB.route;

import com.vijfmusketiers.ProjectB.breadcrumbs.Breadcrumbs;

import java.util.List;

public class RouteDto {
    private Long id;

    private String name;

    private List<Breadcrumbs> route;

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRoute(List<Breadcrumbs> route) {
        this.route = route;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Breadcrumbs> getRoute() {
        return route;
    }
}
