package com.vijfmusketiers.ProjectB.rollator.service;

import com.vijfmusketiers.ProjectB.rollator.Rollators;
import com.vijfmusketiers.ProjectB.rollator.exception.RollatorNotFound;
import com.vijfmusketiers.ProjectB.rollator.repository.RollatorRepository;
import com.vijfmusketiers.ProjectB.route.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RollatorService implements RollatorServiceInterface {

    @Autowired
    private RollatorRepository rollatorRepository;

    @Override
    public Rollators getRollators(Long rollatorid) {
        return rollatorRepository.getById(rollatorid);
    }

    @Override
    public List<Rollators> getAllRollators() {
        return rollatorRepository.getAllByIdNotNull();
    }

    @Override
    public Rollators save(Rollators r) { return rollatorRepository.save(r); }

    @Override
    public Rollators updateById(Rollators r, Long rollatorid) {
        Rollators rollator = rollatorRepository.findDistinctById(rollatorid);
        rollator.setName(r.getName());

        return rollatorRepository.save(rollator);
    }

    @Override
    public Boolean deleteById(Long rollatorId) {
        Rollators r = rollatorRepository.findById(rollatorId)
                .orElseThrow(() -> new RollatorNotFound(rollatorId));

        return rollatorRepository.deleteDistinctById(r.getId());
    }
}
