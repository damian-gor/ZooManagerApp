package com.ZooManagerApp.controller;

import com.ZooManagerApp.model.Division;
import com.ZooManagerApp.model.Schedule;
import com.ZooManagerApp.model.Species;
import com.ZooManagerApp.service.ScheduleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @ApiOperation(value= "Returns list of schedules.",
            response = Schedule.class)
    @GetMapping("/divisions/{divisionId}/species/{speciesId}/schedules")
    public ResponseEntity<List<Schedule>> getAllSchedules(){
        return ResponseEntity.ok().body(scheduleService.getAllSchedules());
    }

    @GetMapping("/divisions/{divisionId}/species/{speciesId}/schedules/{scheduleId}")
    @ApiOperation(value= "Finds schedule by ID",
            notes = "Provide an ID to look up specific schedule.",
            response = Schedule.class)
    public ResponseEntity<Schedule> getSchedule(
            @ApiParam(value= "Requested schedule ID.", required = true)@PathVariable long scheduleId){
        return ResponseEntity.ok().body(scheduleService.getSchedule(scheduleId));
    }

    @PostMapping("/divisions/{divisionId}/species/{speciesId}/schedules")
    @ApiOperation(value= "Adds new schedule.",
            notes = "Enter parameters of new schedule.",
            response = Schedule.class)
    public ResponseEntity<Schedule> addSchedule(
            @ApiParam(value= "ID of the species to which the new schedule applies.", required = true)@PathVariable long speciesId,
            @ApiParam(value= "New schedule's parameters (enter only 'details' and the date of the appropriate schedule's " +
                    "type.).", required = true)@RequestBody Schedule schedule){
        schedule.setSpeciesId(speciesId);
        return ResponseEntity.ok().body(scheduleService.addSchedule(schedule));
    }

    @PutMapping("/divisions/{divisionId}/species/{speciesId}/schedules/{scheduleId}")
    @ApiOperation(value= "Updates selected schedule.",
            notes = "Enter ID and new parameters of existing schedule.",
            response = Schedule.class)
    public ResponseEntity<Schedule> updateSchedule(
            @ApiParam(value= "ID of the species to which the updated schedule applies.", required = true)@PathVariable long speciesId,
            @ApiParam(value= "ID of the schedule you want to update.", required = true)@PathVariable long scheduleId,
            @ApiParam(value= "Updated schedule's parameters (enter only 'details' and the date of the appropriate schedule's " +
                    "type.).", required = true)@RequestBody Schedule schedule){
        schedule.setId(scheduleId);
        schedule.setSpeciesId(speciesId);
        return ResponseEntity.ok().body(scheduleService.updateSchedule(schedule));
    }

    @DeleteMapping("/divisions/{divisionId}/species/{speciesId}/schedules/{scheduleId}")
    @ApiOperation(value= "Deletes selected schedule.",
            notes = "Enter the schedule ID you want to delete.",
            response = HttpStatus.class)
    public HttpStatus removeSchedule(
            @ApiParam(value= "ID of the schedule you want to delete.", required = true) @PathVariable long scheduleId){
        scheduleService.removeSchedule(scheduleId);
        return HttpStatus.OK;
    }
}
