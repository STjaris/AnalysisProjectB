package com.vijfmusketiers.ProjectB.rollator;

import com.vijfmusketiers.ProjectB.route.Route;

import java.util.Set;

public class RollatorsDto {
    private Long id;
    private String name;
    private Set<Route> route;

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRoute(Set<Route> route) {
        this.route = route;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Set<Route> getRoute() {
        return route;
    }
}
