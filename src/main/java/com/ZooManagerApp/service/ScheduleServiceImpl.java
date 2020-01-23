package com.ZooManagerApp.service;

import com.ZooManagerApp.exception.ResourceNotFoundException;
import com.ZooManagerApp.model.Schedule;
import com.ZooManagerApp.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getSchedule(long scheduleId) {
        Optional<Schedule> scheduleDb = scheduleRepository.findById(scheduleId);

        if (scheduleDb.isPresent())
        {
            return scheduleDb.get();
        }
        else{
            throw new ResourceNotFoundException ("Have not found schedule with id : " + scheduleId);
        }
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) {
        Optional<Schedule> scheduleDb = scheduleRepository.findById(schedule.getId());
        if (scheduleDb.isPresent())
        {
            Schedule updatedSchedule = scheduleDb.get();
            updatedSchedule.setId(schedule.getId());
            updatedSchedule.setCleaningDate(schedule.getCleaningDate());
            updatedSchedule.setFeedingDate(schedule.getFeedingDate());
            updatedSchedule.setPerformanceDate(schedule.getPerformanceDate());
            updatedSchedule.setDetails(schedule.getDetails());
            scheduleRepository.save(updatedSchedule);
            return updatedSchedule;
        }
        else{
            throw new ResourceNotFoundException ("Have not found schedule with id : " + schedule.getId());
        }
    }

    @Override
    public Schedule addSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
        return schedule;
    }

    @Override
    public void removeSchedule(long scheduleId) {
        Optional <Schedule> scheduleDb = scheduleRepository.findById(scheduleId);

        if (scheduleDb.isPresent()){
            scheduleRepository.deleteById(scheduleId);
        }
        else{
            throw new ResourceNotFoundException("Have not found schedule with id : " + scheduleId);
        }
    }
}
