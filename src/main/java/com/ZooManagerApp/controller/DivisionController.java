package com.ZooManagerApp.controller;

import com.ZooManagerApp.model.Division;
import com.ZooManagerApp.service.DivisionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DivisionController {

    @Autowired
    private DivisionService divisionService;

    @GetMapping("/divisions")
    @ApiOperation(value= "Returns list of divisions.",
            response = Division.class)
    public ResponseEntity<List<Division>> getAllDivisions(){
        return ResponseEntity.ok().body(divisionService.getAllDivisions());
    }

    @GetMapping("/divisions/{id}")
    @ApiOperation(value= "Finds division by ID",
            notes = "Provide an ID to look up specific division from the zoo.",
            response = Division.class)
    public ResponseEntity<Division> getDivision(
            @ApiParam(value= "Requested division ID.", required = true)@PathVariable long id){
        return ResponseEntity.ok().body(divisionService.getDivision(id));
    }

    @PostMapping("/divisions")
    @ApiOperation(value= "Adds new division.",
            notes = "Enter parameters of new division.",
            response = Division.class)
    public ResponseEntity<Division> addDivision(
            @ApiParam(value= "New division's parameters (skip 'id' parameter).", required = true)@RequestBody Division division){
        return ResponseEntity.ok().body(divisionService.addDivision(division));
    }

    @PutMapping("/divisions/{id}")
    @ApiOperation(value= "Updates selected division.",
            notes = "Enter ID and new parameters of existing division.",
            response = Division.class)
    public ResponseEntity<Division> updateDivision(
            @ApiParam(value= "Requested division ID.", required = true)@PathVariable long id,
            @ApiParam(value= "New division's parameters (skip 'id' parameter).", required = true)@RequestBody Division division){
        division.setId(id);
        return ResponseEntity.ok().body(divisionService.updateDivision(division));
    }

    @DeleteMapping("/divisions/{id}")
    @ApiOperation(value= "Deletes selected division.",
            notes = "Enter the division ID you want to delete.",
            response = HttpStatus.class)
    public HttpStatus removeDivision(
            @ApiParam(value= "ID of the division you want to delete.", required = true) @PathVariable long id){
        divisionService.removeDivision(id);
        return HttpStatus.OK;
    }

}
