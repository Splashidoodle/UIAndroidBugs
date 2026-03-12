package com.german_software_engineers.trainerapp.Controller;

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

import android.support.annotation.NonNull;

import com.german_software_engineers.trainerapp.ExerciseView.Activity.ExerciseViewActivity;
import com.german_software_engineers.trainerappmodel.Model.Model;

public class ExerciseListModelController {
    private ExerciseViewActivity ExerciseView;
    private Model ApplicationModel;

    public ExerciseListModelController(@NonNull ExerciseViewActivity activity,@NonNull Model applicationModel){
        ApplicationModel=applicationModel;
        ExerciseView=activity;
    }

    public void moveExerciseUp(int position){
        ApplicationModel.moveExerciseOfActiveScheduleUp(position);
        ExerciseView.updateView();
    }

    public void moveExerciseDown(int position){
        ApplicationModel.moveExerciseOfActiveScheduleDown(position);
        ExerciseView.updateView();
    }

    public void deleteExercise(int position){
        ApplicationModel.deleteExerciseOfActiveSchedule(position);
        ExerciseView.updateView();
    }

    public void editExercise(String exerciseName){
        ExerciseView.openExerciseEditor(exerciseName);
    }
}
