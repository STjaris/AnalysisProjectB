package com.vijfmusketiers.ProjectB.unity.service;

import com.vijfmusketiers.ProjectB.route.Route;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public interface UnityServiceInterface {
    Map getAllnamesAndId();
    Map getAllnamesAndIdByUser(Long userId);
    Map getRoute(Long routeid) throws JSONException;
}
