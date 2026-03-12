package com.german_software_engineers.trainerappmodel.Schedule;

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

import com.german_software_engineers.trainerappmodel.Enumerations.*;
import com.german_software_engineers.trainerappmodel.Exercise.Exercise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Schedule {
    private String Name;
    private TrainingsTypes TrainingsType = TrainingsTypes.invalid;
    private int Repetitions = Integer.MAX_VALUE;
    private int PauseTime = Integer.MAX_VALUE;
    private int Sets = Integer.MAX_VALUE;
    private int Speed = Integer.MAX_VALUE;
    private Map<Integer,Exercise> Exercises;

    public Schedule(String name){
        Name = name;
        Exercises=new TreeMap<>();
    }

    public String getName() {
        return Name;
    }

    public void setTrainingsType(TrainingsTypes trainingsType){
        TrainingsType=trainingsType;
    }

    public TrainingsTypes getTrainingsType() {
        return TrainingsType;
    }

    public void setRepetitions(int repetitions){
        Repetitions = repetitions;
    }

    public int getRepetitions() {
        return Repetitions;
    }

    public void setPauseTime(int pauseTime){
        PauseTime=pauseTime;
    }

    public int getPauseTime() {
        return PauseTime;
    }

    public void setSets(int sets){
        Sets = sets;
    }

    public int getSets() {
        return Sets;
    }

    public void setSpeed(int speed){
        Speed=speed;
    }

    public int getSpeed() {
        return Speed;
    }

    public Collection<Exercise> exercises(){
        return Exercises.values();
    }

    public void addExercise(Exercise exercise){
        if(exercise.getPosition()==Integer.MAX_VALUE)
            exercise.setPosition(Exercises.size());
        Exercises.put(exercise.getPosition(),exercise);
    }

    public void moveExerciseUp(int position){
        if(position-1>=0) {
            Exercise exercise1 = Exercises.get(position);
            Exercise exercise2 = Exercises.get(position - 1);
            Exercises.remove(position);
            Exercises.remove(position-1);
            exercise1.setPosition(position-1);
            exercise2.setPosition(position);
            Exercises.put(exercise1.getPosition(),exercise1);
            Exercises.put(exercise2.getPosition(),exercise2);
        }
    }

    public void moveExerciseDown(int position){
        if(position+1<Exercises.size()) {
            Exercise exercise1 = Exercises.get(position);
            Exercise exercise2 = Exercises.get(position +1);
            Exercises.remove(position);
            Exercises.remove(position+1);
            exercise1.setPosition(position+1);
            exercise2.setPosition(position);
            Exercises.put(exercise1.getPosition(),exercise1);
            Exercises.put(exercise2.getPosition(),exercise2);
        }
    }

    public void deleteExercise(int position){
        if((position>=0)&&(position<Exercises.size())){
            Exercises.remove(position);
            for(int i = position; i<Exercises.size()-1; i++){
                Exercise exercise = Exercises.get(i+1);
                exercise.setPosition(i);
                Exercises.put(exercise.getPosition(),exercise);
            }
        }
    }
}