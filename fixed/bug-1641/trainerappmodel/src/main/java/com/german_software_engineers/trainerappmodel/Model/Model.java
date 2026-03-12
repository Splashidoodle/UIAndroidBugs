package com.german_software_engineers.trainerappmodel.Model;

/**
 *     Copyright (C) 2019  Moritz Herzog
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>
 */

import com.german_software_engineers.trainerappmodel.Schedule.Schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.german_software_engineers.trainerappmodel.Exceptions.ScheduleAvailableException;

public class Model {
    private String ApplicationVersion;
    private Map<String, Schedule> Schedules;
    private Schedule ActiveSchedule;

    public Model(){
        Schedules=new HashMap<>();
    }

    public void addSchedule(Schedule schedule) throws ScheduleAvailableException{
        if(Schedules.containsKey(schedule.getName()))
            throw new ScheduleAvailableException();
        Schedules.put(schedule.getName(),schedule);
    }

    public Schedule getSchedule(String name) throws ScheduleAvailableException{
        if(Schedules.containsKey(name)){
            return Schedules.get(name);
        }
        throw new ScheduleAvailableException();
    }

    public List<Schedule> getSchedulesList(){
        List<Schedule> returnValue = new ArrayList<>();
        returnValue.addAll(Schedules.values());
        return returnValue;
    }

    public void deleteSchedule(String name){
        Schedules.remove(name);
    }

    public void setApplicationVersion(String applicationVersion) {
        ApplicationVersion = applicationVersion;
    }

    public void setActiveSchedule(Schedule schedule){
        ActiveSchedule = schedule;
    }

    public Schedule activeSchedule(){
        return ActiveSchedule;
    }

    public void moveExerciseOfActiveScheduleUp(int position){
        ActiveSchedule.moveExerciseUp(position);
    }

    public void moveExerciseOfActiveScheduleDown(int position){
        ActiveSchedule.moveExerciseDown(position);
    }

    public void deleteExerciseOfActiveSchedule(int position){
        ActiveSchedule.deleteExercise(position);
    }
}
