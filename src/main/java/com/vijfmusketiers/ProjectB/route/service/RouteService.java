package com.vijfmusketiers.ProjectB.route.service;

import com.vijfmusketiers.ProjectB.authentication.user.User;
import com.vijfmusketiers.ProjectB.authentication.user.UserDto;
import com.vijfmusketiers.ProjectB.breadcrumbs.repository.BreadcrumbRepository;
import com.vijfmusketiers.ProjectB.rollator.service.RollatorServiceInterface;
import com.vijfmusketiers.ProjectB.route.Route;
import com.vijfmusketiers.ProjectB.route.RouteDto;
import com.vijfmusketiers.ProjectB.route.repository.RouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RouteService implements RouteServiceInterface {

    private ModelMapper modelMapper;
    private RouteRepository routeRepository;
    private BreadcrumbRepository breadcrumbRepository;
    private RollatorServiceInterface rollatorServiceInterface;

    @Autowired
    public RouteService(RouteRepository routeRepository, BreadcrumbRepository breadcrumbRepository, RollatorServiceInterface rollatorServiceInterface, ModelMapper modelMapper) {
        this.routeRepository = routeRepository;
        this.breadcrumbRepository = breadcrumbRepository;
        this.rollatorServiceInterface = rollatorServiceInterface;
        this.modelMapper = modelMapper;
    }

    @Override
    public Map getAllRoutes() {

        List<Route> l = routeRepository.findAll();
        Map<Long, Route> m = new HashMap();

        for (Route route : l) {
            try {
                UserDto userDto = modelMapper.map(route.getUser(), UserDto.class);
                User user = modelMapper.map(userDto, User.class);
                route.setUser(user);

                m.put(route.getId(), route);
            }
            catch(Exception e) { }
        }
        return m;
    }

    @Override
    public Map getAllroutesOnly() {

        List<Route> l = routeRepository.findAll();
        Map<Long, RouteDto> m = new HashMap();

        for (Route route : l) {

            RouteDto routeDto = modelMapper.map(route, RouteDto.class);
            m.put(route.getId(), routeDto);
        }
        return m;
    }

    @Override
    public Map getRoutesByUsers(Long usersid) {
        List<Route> l = routeRepository.findAllByUserId(usersid);
        Map<Long, Route> m = new HashMap();

        for (Route route : l) {
            UserDto userDto = modelMapper.map(route.getUser(), UserDto.class);
            User user = modelMapper.map(userDto, User.class);
            route.setUser(user);
            m.put(route.getId(), route);
        }

        return m;
    }

    public Map getAllnamesAndId() {

        List<Route> l = routeRepository.findAll();
        Map<Long, String> m = new HashMap();

        for (Route route : l) {
            UserDto userDto = modelMapper.map(route.getUser(), UserDto.class);
            User user = modelMapper.map(userDto, User.class);
            route.setUser(user);

            m.put(route.getId(), route.getName());
        }

        return m;
    }


    @Override
    public Route getRoute(Long routeid) {
        Route route = routeRepository.getById(routeid);
        UserDto userDto = modelMapper.map(route.getUser(), UserDto.class);
        User user = modelMapper.map(userDto, User.class);
        route.setUser(user);

        return route;
    }

    @Override
    public Route save(Route r) {
        return routeRepository.save(r);
    }

    @Override
    public Route updateById(Route r, Long routeid) {

        Route route  = routeRepository.findDistinctById(routeid);

        route.setName(r.getName());
        route.setRoute(r.getRoute());


        return routeRepository.save(route);
    }

    @Override
    public Boolean deleteById(Long routeid) {

        Route r = routeRepository.findDistinctById(routeid);
        breadcrumbRepository.deleteDistinctByRoute(r);

        return routeRepository.deleteDistinctById(routeid);
    }
}
