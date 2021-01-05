package com.vijfmusketiers.ProjectB.route.service;

import com.vijfmusketiers.ProjectB.route.Route;

import java.util.Map;

public interface RouteServiceInterface {

    Map getAllRoutes();

    Map getAllnamesAndId();

    Map getRoutesByUsers(Long usersid);

    Map getAllroutesOnly();

    Route getRoute(Long routeid);

    Route save(Route r);

    Route updateById(Route r, Long routeid);

    Boolean deleteById(Long routeid);

}
