package com.german_software_engineers.trainerapp.ScheduleView;

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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.german_software_engineers.trainerapp.Controller.ApplicationManager;
import com.german_software_engineers.trainerapp.ExerciseView.Activity.ExerciseViewActivity;
import com.german_software_engineers.trainerapp.R;
import com.german_software_engineers.trainerappmodel.Enumerations.TrainingsTypes;
import com.german_software_engineers.trainerappmodel.Schedule.Schedule;
import com.german_software_engineers.trainerappmodel.Exceptions.ScheduleAvailableException;

public class GeneralTrainingScheduleEditor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_training_schedule_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> openNextActivity());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(((ApplicationManager)getApplication()).getApplicationModel().activeSchedule()!=null){
            decorateGuiWithActiveSchedule();
        }
    }

    private void openNextActivity(){
        if(addScheduleToModel()) {
            ((ApplicationManager)getApplication()).saveFile();
            Intent intent = new Intent(this, ExerciseViewActivity.class);
            startActivity(intent);
        }
    }

    private void decorateGuiWithActiveSchedule(){
        Schedule activeSchedule = ((ApplicationManager)getApplication()).getApplicationModel().activeSchedule();
        ((EditText) findViewById(R.id.nameTextEdit)).setText(activeSchedule.getName());
        findViewById(R.id.nameTextEdit).setEnabled(false);

        if(activeSchedule.getRepetitions()!=Integer.MAX_VALUE)
            ((EditText) findViewById(R.id.repEdit)).setText(String.valueOf(activeSchedule.getRepetitions()));

        if(activeSchedule.getPauseTime()!=Integer.MAX_VALUE)
            ((EditText) findViewById(R.id.pauseEdit)).setText(String.valueOf(activeSchedule.getPauseTime()));

        if(activeSchedule.getSets()!=Integer.MAX_VALUE)
            ((EditText) findViewById(R.id.setEdit)).setText(String.valueOf(activeSchedule.getSets()));

        if(activeSchedule.getSpeed()!=Integer.MAX_VALUE)
            ((EditText) findViewById(R.id.speedEdit)).setText(String.valueOf(activeSchedule.getSpeed()));
    }

    private boolean addScheduleToModel() {
        if (((EditText) findViewById(R.id.nameTextEdit)).getText().toString().isEmpty()) {
            ((EditText) findViewById(R.id.nameTextEdit)).setError(getString(R.string.NoNameError));
            return false;
        }
        Schedule schedule;

        if(((ApplicationManager)getApplication()).getApplicationModel().activeSchedule()!=null)
            schedule = ((ApplicationManager)getApplication()).getApplicationModel().activeSchedule();
        else
            schedule = new Schedule(((EditText) findViewById(R.id.nameTextEdit)).getText().toString());

        schedule.setTrainingsType(TrainingsTypes.values()[(int)((Spinner)findViewById(R.id.trainSpinner)).getSelectedItemId()]);

        Integer reps = 0;
        if (!((EditText) findViewById(R.id.repEdit)).getText().toString().isEmpty()) {
            reps = Integer.valueOf(((EditText) findViewById(R.id.repEdit)).getText().toString());
            schedule.setRepetitions(reps);
        }

        Integer pause = 0;
        if (!((EditText) findViewById(R.id.pauseEdit)).getText().toString().isEmpty()) {
            pause = Integer.valueOf((((EditText) findViewById(R.id.pauseEdit)).getText().toString()));
            schedule.setPauseTime(pause);
        }

        Integer sets = 0;
        if (!((EditText) findViewById(R.id.setEdit)).getText().toString().isEmpty()) {
            sets = Integer.valueOf(((EditText) findViewById(R.id.setEdit)).getText().toString());
            schedule.setSets(sets);
        }

        Integer speed = 0;
        if (!((EditText) findViewById(R.id.speedEdit)).getText().toString().isEmpty()) {
            speed = Integer.valueOf(((EditText) findViewById(R.id.speedEdit)).getText().toString());
            schedule.setSpeed(speed);
        }

        if (((ApplicationManager) getApplication()).getApplicationModel().activeSchedule() == null) {
            try {
                ((ApplicationManager) getApplication()).getApplicationModel().addSchedule(schedule);
                ((ApplicationManager) getApplication()).getApplicationModel().setActiveSchedule(schedule);
            } catch (ScheduleAvailableException e) {
                e.printStackTrace();
            }
            return true;
        }else{
            return true;
        }
    }
}
