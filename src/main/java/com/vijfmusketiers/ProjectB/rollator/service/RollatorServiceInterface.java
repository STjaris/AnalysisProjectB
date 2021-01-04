package com.vijfmusketiers.ProjectB.rollator.service;

import com.vijfmusketiers.ProjectB.rollator.Rollators;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RollatorServiceInterface {
    Rollators getRollators(Long rollatorid);

    Rollators save(Rollators r);

    List<Rollators> getAllRollators();

    Boolean deleteById(Long rollatorId);

    Rollators updateById(Rollators r, Long rollatorid);
}
