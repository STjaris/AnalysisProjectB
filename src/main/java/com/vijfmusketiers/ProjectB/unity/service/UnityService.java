package com.vijfmusketiers.ProjectB.unity.service;

import com.vijfmusketiers.ProjectB.breadcrumbs.repository.BreadcrumbRepository;
import com.vijfmusketiers.ProjectB.route.Route;
import com.vijfmusketiers.ProjectB.route.repository.RouteRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class UnityService implements UnityServiceInterface{

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private BreadcrumbRepository breadcrumbRepository;

    @Override
    public Map getAllnamesAndId() {
        List<Route> l = routeRepository.findAll();
        Map<Long, String> m = new HashMap();

        for (Route route : l) {
            m.put(route.getId(), route.getName());
        }

        return m;
    }

    @Override
    public Map getAllnamesAndIdByUser(Long userId) {
        List<Route> l = routeRepository.findAllByUserId(userId);
        Map<Long, String> m = new HashMap();

        for (Route route : l) {
            m.put(route.getId(), route.getName());
        }

        return m;
    }

    @Override
    public Map getRoute(Long routeid) throws JSONException {
        Route r = routeRepository.getById(routeid);
        Map routeReturn = new LinkedHashMap();

        routeReturn.put("id", r.getId());
        routeReturn.put("name", r.getName());
        routeReturn.put("route", r.getRoute());

        return routeReturn;
    }


}
