package com.vijfmusketiers.ProjectB.breadcrumbs.controller;

import com.vijfmusketiers.ProjectB.breadcrumbs.Breadcrumbs;
import com.vijfmusketiers.ProjectB.breadcrumbs.service.BreadcrumbServiceInterface;
import com.vijfmusketiers.ProjectB.route.service.RouteServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BreadcrumbController {

    private final BreadcrumbServiceInterface breadcrumbServiceInterface;
    private final RouteServiceInterface routeServiceInterface;

    @Autowired
    public BreadcrumbController(BreadcrumbServiceInterface breadcrumbServiceInterface, RouteServiceInterface routeServiceInterface) {
        this.breadcrumbServiceInterface = breadcrumbServiceInterface;
        this.routeServiceInterface = routeServiceInterface;
    }

    @PostMapping(
            value = "/breadcrumbs",
            consumes = "application/json",
            produces = "application/json")
    public Boolean saveBreadcrumbs(@RequestBody Map<Long, List<Long[]>> m) {
        return templateSave(m);
    }


    @PutMapping("/breadcrumbs/{routeid}")
    public Boolean updateBreadcrumbs(@PathVariable Long routeid, @RequestBody Map<Long, List<Long[]>> m) {

        breadcrumbServiceInterface.deleteBreadcrumbs(routeid);

        return templateSave(m);
    }

    public Boolean templateSave(Map<Long, List<Long[]>> m) {
        Map.Entry<Long, List<Long[]>> entry = m.entrySet().iterator().next();

        List<Long[]> lists = m.get(m.keySet().toArray()[0]);

        for (Long[] l : lists) {

            Breadcrumbs b = new Breadcrumbs();

            for (Long i : l) {

                b.setxCo(l[0]);
                b.setyCo(l[1]);
                b.setRoute(routeServiceInterface.getRoute(entry.getKey()));

                breadcrumbServiceInterface.saveBreadcrumbs(b);

            }
            System.out.println(b.toString());
        }

        return true;
    }
}
