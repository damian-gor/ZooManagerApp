package com.ZooManagerApp.service;

import com.ZooManagerApp.model.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getAllSchedules();
    Schedule getSchedule(long scheduleId);
    Schedule updateSchedule(Schedule schedule);
    Schedule addSchedule(Schedule schedule);
    void removeSchedule(long scheduleId);
}
