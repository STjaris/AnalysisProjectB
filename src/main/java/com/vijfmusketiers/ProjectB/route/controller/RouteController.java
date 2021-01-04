package com.vijfmusketiers.ProjectB.route.controller;


import com.vijfmusketiers.ProjectB.authentication.user.User;
import com.vijfmusketiers.ProjectB.authentication.user.UserDto;
import com.vijfmusketiers.ProjectB.authentication.user.repository.UserRepository;
import com.vijfmusketiers.ProjectB.breadcrumbs.Breadcrumbs;
import com.vijfmusketiers.ProjectB.breadcrumbs.repository.BreadcrumbRepository;
import com.vijfmusketiers.ProjectB.route.Route;
import com.vijfmusketiers.ProjectB.route.RouteDto;
import com.vijfmusketiers.ProjectB.route.service.RouteServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Validated
public class RouteController {

    private final RouteServiceInterface routeServiceInterface;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BreadcrumbRepository breadCrumbsRepository;

    @Autowired
    public RouteController(RouteServiceInterface routeServiceInterface, UserRepository userRepository, ModelMapper modelMapper, BreadcrumbRepository breadCrumbsRepository) {
        this.routeServiceInterface = routeServiceInterface;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.breadCrumbsRepository = breadCrumbsRepository;
    }

    @GetMapping("/routes")
    public Map getAllRoutes() {
        return routeServiceInterface.getAllRoutes();
    }

    @GetMapping("/routenames")
    public Map getAllnamesAndId() {
        return routeServiceInterface.getAllnamesAndId();
    }

    @GetMapping("/routesonly")
    public Map getAllroutesOnly() {
        return routeServiceInterface.getAllroutesOnly();
    }

    @GetMapping("routes/{routeid}")
    public Route getRoute(@PathVariable Long routeid) {
        return routeServiceInterface.getRoute(routeid);
    }

//    @GetMapping("routesbyuser")
//    public Map getRouteByUsers(Principal principal) {
//        User user = userRepository.findByEmail(principal.getName());
//        return routeServiceInterface.getRoutesByUsers(user.getId());
//    }

    @GetMapping("routesbyuser/{usersid}")
    public Map getRouteByUsers(@PathVariable Long usersid) {
        return routeServiceInterface.getRoutesByUsers(usersid);
    }

    @PostMapping(
            value = "/routes",
            consumes = "application/json",
            produces = "application/json")
    public Route saveRoute(@RequestBody Route route) {
        return routeServiceInterface.save(route);
    }

    @PostMapping(
            value = "/routes/{userid}",
            consumes = "application/json",
            produces = "application/json")
    public Route saveRouteWithUser(@RequestBody Route route, @PathVariable Long userid) {
        route.setUser(userRepository.findByid(userid));
        routeServiceInterface.save(route);

        //User terug geven in vorm van userDto
        UserDto userDto = modelMapper.map(userRepository.findByid(userid), UserDto.class);
        User user = modelMapper.map(userDto, User.class);
        route.setUser(user);
        route.getUser().setId(userid);

        //Opslaan van de breadcrumbs
        for (Breadcrumbs bc : route.getRoute()) {
            bc.setRoute(route);
            breadCrumbsRepository.save(bc);
        }
        return route;
    }

    @PutMapping("/routes/{routeid}")
    public RouteDto updateRoute(@PathVariable Long routeid, @RequestBody Route route) {
        return modelMapper.map(routeServiceInterface.updateById(route, routeid), RouteDto.class);
    }

    @DeleteMapping("/routes/{routeid}")
    public Boolean deleteRoute(@PathVariable Long routeid) {
        return routeServiceInterface.deleteById(routeid);
    }
}
