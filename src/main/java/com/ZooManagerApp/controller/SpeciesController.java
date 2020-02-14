package com.ZooManagerApp.controller;

import com.ZooManagerApp.model.Division;
import com.ZooManagerApp.model.Species;
import com.ZooManagerApp.service.SpeciesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value= "Returns list of species.",
            response = Species.class)
    public ResponseEntity<List<Species>> getAllSpecies(){
        return ResponseEntity.ok().body(speciesService.getAllSpecies());
    }

    @GetMapping("/divisions/{divisionId}/species/{speciesId}")
    @ApiOperation(value= "Finds species by ID",
            notes = "Provide an ID to look up specific species from the zoo.",
            response = Species.class)
    public ResponseEntity<Species> getSpecies(
            @ApiParam(value= "Requested species ID.", required = true)@PathVariable long speciesId){
        return ResponseEntity.ok().body(speciesService.getSpecies(speciesId));
    }

    @PostMapping("/divisions/{divisionId}/species")
    @ApiOperation(value= "Adds new species.",
            notes = "Enter parameters of new species.",
            response = Species.class)
    public ResponseEntity<Species> addSpecies(
            @ApiParam(value= "ID of the new species' division.", required = true)@PathVariable long divisionId,
            @ApiParam(value= "New species' parameters (skip 'id' parameter).", required = true) @RequestBody Species species){
        species.setDivisionId(divisionId);
        return ResponseEntity.ok().body(speciesService.addSpecies(species));
    }

    @PutMapping("/divisions/{divisionId}/species/{speciesId}")
    @ApiOperation(value= "Updates selected species.",
            notes = "Enter ID and new parameters of existing species.",
            response = Species.class)
    public ResponseEntity<Species> updateSpecies(
            @ApiParam(value= "ID of the species you want to update.", required = true)@PathVariable long speciesId,
            @ApiParam(value= "New species' parameters (skip 'id' parameter).", required = true) @RequestBody Species species){
        species.setId(speciesId);
        species.setDivisionId(species.getDivisionId());
        return ResponseEntity.ok().body(speciesService.updateSpecies(species));
    }

    @DeleteMapping("/divisions/{divisionId}/species/{speciesId}")
    @ApiOperation(value= "Deletes selected species.",
            notes = "Enter the species ID you want to delete.",
            response = HttpStatus.class)
    public HttpStatus removeSpecies(
            @ApiParam(value= "ID of the species you want to delete.", required = true) @PathVariable long speciesId){
        speciesService.removeSpecies(speciesId);
        return HttpStatus.OK;
    }

}
