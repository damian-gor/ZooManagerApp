package com.ZooManagerApp.controller;

import com.ZooManagerApp.model.Schedule;
import com.ZooManagerApp.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/divisions/{divisionId}/species/{speciesId}/schedules")
    public ResponseEntity<List<Schedule>> getAllSchedules(){
        return ResponseEntity.ok().body(scheduleService.getAllSchedules());
    }

    @GetMapping("/divisions/{divisionId}/species/{speciesId}/schedules/{scheduleId}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable long scheduleId){
        return ResponseEntity.ok().body(scheduleService.getSchedule(scheduleId));
    }

    @PostMapping("/divisions/{divisionId}/species/{speciesId}/schedules")
    public ResponseEntity<Schedule> addSchedule(@PathVariable long speciesId, @RequestBody Schedule schedule){
        schedule.setSpeciesId(speciesId);
        return ResponseEntity.ok().body(scheduleService.addSchedule(schedule));
    }

    @PutMapping("/divisions/{divisionId}/species/{speciesId}/schedules/{scheduleId}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable long speciesId, @PathVariable long scheduleId, @RequestBody Schedule schedule){
    schedule.setId(scheduleId);
    schedule.setSpeciesId(speciesId);
        return ResponseEntity.ok().body(scheduleService.updateSchedule(schedule));
    }

    @DeleteMapping("/divisions/{divisionId}/species/{speciesId}/schedules/{scheduleId}")
    public HttpStatus removeSchedule(@PathVariable long scheduleId){
        scheduleService.removeSchedule(scheduleId);
        return HttpStatus.OK;
    }
}











