package com.ZooManagerApp.controller;

import com.ZooManagerApp.model.Species;
import com.ZooManagerApp.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpeciesController {

    @Autowired
    private SpeciesService speciesService;

    @GetMapping("/divisions/{divisionId}/species")
    public ResponseEntity<List<Species>> getAllSpecies(){
        return ResponseEntity.ok().body(speciesService.getAllSpecies());
    }

    @GetMapping("/divisions/{divisionId}/species/{speciesId}")
    public ResponseEntity<Species> getSpecies(@PathVariable long speciesId){
        return ResponseEntity.ok().body(speciesService.getSpecies(speciesId));
    }

    @PostMapping("/divisions/{divisionId}/species")
    public ResponseEntity<Species> addSpecies(@PathVariable long divisionId, @RequestBody Species species){
        species.setDivisionId(divisionId);
        return ResponseEntity.ok().body(speciesService.addSpecies(species));
    }

    @PutMapping("/divisions/{divisionId}/species/{speciesId}")
    public ResponseEntity<Species> updateSpecies(@PathVariable long divisionId, @PathVariable long speciesId, @RequestBody Species species){
    species.setId(speciesId);
    species.setDivisionId(divisionId);
        return ResponseEntity.ok().body(speciesService.updateSpecies(species));
    }

    @DeleteMapping("/divisions/{divisionId}/species/{speciesId}")
    public HttpStatus removeSpecies(@PathVariable long speciesId){
        speciesService.removeSpecies(speciesId);
        return HttpStatus.OK;
    }

}











