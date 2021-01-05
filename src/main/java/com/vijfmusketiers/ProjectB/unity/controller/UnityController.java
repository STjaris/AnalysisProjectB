package com.vijfmusketiers.ProjectB.unity.controller;

import com.vijfmusketiers.ProjectB.route.Route;
import com.vijfmusketiers.ProjectB.route.service.RouteServiceInterface;
import com.vijfmusketiers.ProjectB.unity.service.UnityServiceInterface;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Validated
public class UnityController {

    @Autowired
    private UnityServiceInterface unityService;

    @GetMapping("/unity/routenames")
    public Map getAllnamesAndId() {
        return unityService.getAllnamesAndId();
    }

    @GetMapping("/unity/routenames/{userid}")
    public Map getAllnamesAndIdByUser(@PathVariable Long userid) {
        return unityService.getAllnamesAndIdByUser(userid);
    }

    @GetMapping("/unity/{routeid}")
    public Map getRoute(@PathVariable Long routeid) throws JSONException {
        return unityService.getRoute(routeid);
    }


}
