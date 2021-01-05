package com.vijfmusketiers.ProjectB.rollator.controller;

import com.vijfmusketiers.ProjectB.rollator.Rollators;
import com.vijfmusketiers.ProjectB.rollator.RollatorsDto;
import com.vijfmusketiers.ProjectB.rollator.service.RollatorServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RollatorController {

    private final RollatorServiceInterface rollatorServiceInterface;
    private final ModelMapper modelMapper;

    @Autowired
    public RollatorController(RollatorServiceInterface rollatorServiceInterface, ModelMapper modelMapper) {
        this.rollatorServiceInterface = rollatorServiceInterface;
        this.modelMapper = modelMapper;
    }

    @GetMapping("rollators/{rollatorid}")
    public RollatorsDto getRollator(@PathVariable Long rollatorid) {
        return modelMapper.map(rollatorServiceInterface.getRollators(rollatorid), RollatorsDto.class);
    }

    @GetMapping("rollators")
    public List<Rollators> getAllRollators() {
        return rollatorServiceInterface.getAllRollators();
    }

    @PostMapping(
            value = "/rollators",
            consumes = "application/json",
            produces = "application/json")
    public Rollators saveRollators(@RequestBody Rollators rollators) {
        return rollatorServiceInterface.save(rollators);
    }

    @PutMapping("/rollators/{rollatorid}")
    public Rollators updateRollators(@PathVariable Long rollatorid, @RequestBody Rollators rollators){

        return rollatorServiceInterface.updateById(rollators, rollatorid);
    }

    @DeleteMapping("/rollators/{rollatorId}")
    public Boolean deleteRollator(@PathVariable Long rollatorId) {
        return rollatorServiceInterface.deleteById(rollatorId);
    }
}
