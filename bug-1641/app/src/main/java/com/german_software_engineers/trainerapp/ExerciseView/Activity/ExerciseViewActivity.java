package com.german_software_engineers.trainerapp.ExerciseView.Activity;

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

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.german_software_engineers.trainerapp.Controller.ApplicationManager;
import com.german_software_engineers.trainerapp.Controller.ExerciseListModelController;
import com.german_software_engineers.trainerapp.ExerciseView.Fragments.ExcersizeListFragment;
import com.german_software_engineers.trainerapp.R;
import com.german_software_engineers.trainerappmodel.Exercise.Exercise;
import com.german_software_engineers.trainerappmodel.Schedule.Schedule;

public class ExerciseViewActivity extends ExerciseListActivity {
    ExcersizeListFragment fragment;
    ExerciseListModelController Controller;
    Schedule ActiveSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> addExcersize());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Controller = new ExerciseListModelController(this,((ApplicationManager)getApplication()).getApplicationModel());

        ActiveSchedule = ((ApplicationManager)getApplication()).getApplicationModel().activeSchedule();

        setTitle(ActiveSchedule.getName());
    }


    @Override
    protected void onStart() {
        super.onStart();
        TextView ScheduleInfo = findViewById(R.id.ScheduleInfo);

        fragment = ExcersizeListFragment.newInstance(1, ActiveSchedule.getName(), Controller);
        getSupportFragmentManager().beginTransaction().replace(R.id.execView, fragment).commit();

        String scheduleInfo = String.format(getResources().getString(R.string.ScheduleInfo), ActiveSchedule.getRepetitions(), ActiveSchedule.getPauseTime(), ActiveSchedule.getSets(), ActiveSchedule.getSpeed(), ActiveSchedule.getTrainingsType().toString());
        ScheduleInfo.setText(scheduleInfo);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void addExcersize() {
        Intent intent = new Intent(this, EditExerciseActivity.class);
        intent.putExtra("excName", "");
        startActivity(intent);
    }

    @Override
    public void onListFragmentInteraction(Exercise item) {
        openExerciseEditor(item.getName());
    }

    /**
     * Updates the view.
     */
    public void updateView(){
        fragment = ExcersizeListFragment.newInstance(1, ActiveSchedule.getName(), Controller);
        getSupportFragmentManager().beginTransaction().replace(R.id.execView, fragment).commit();
        ((ApplicationManager)getApplication()).saveFile();
    }

    /**
     * Opens the exercise editor with the exercise with that name.
     * @param exerciseName Name of the exercise you want to edit.
     */
    public void openExerciseEditor(String exerciseName){
        Intent intent = new Intent(this, EditExerciseActivity.class);
        intent.putExtra("excName", exerciseName);
        startActivity(intent);
    }
}
