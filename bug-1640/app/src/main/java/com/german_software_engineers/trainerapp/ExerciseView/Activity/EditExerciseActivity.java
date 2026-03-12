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
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.german_software_engineers.trainerapp.Controller.ApplicationManager;
import com.german_software_engineers.trainerapp.ExerciseView.Fragments.BodyWeightExerciseFragment;
import com.german_software_engineers.trainerapp.ExerciseView.Fragments.DeviceExerciseFragment;
import com.german_software_engineers.trainerapp.ExerciseView.Fragments.ExerciseFragment;
import com.german_software_engineers.trainerapp.ExerciseView.Fragments.WarmUpExerciseFragment;
import com.german_software_engineers.trainerapp.ExerciseView.ViewModel.ExerciseViewModel;
import com.german_software_engineers.trainerapp.R;
import com.german_software_engineers.trainerappmodel.Exercise.Exercise;
import com.german_software_engineers.trainerappmodel.Schedule.Schedule;

public class EditExerciseActivity extends AppCompatActivity implements ExerciseFragment.OnFragmentInteractionListener {

    private ExerciseViewModel ViewModel = null;

    private BodyWeightExerciseFragment bodyWeightExerciseFragment = BodyWeightExerciseFragment.newInstance();
    private DeviceExerciseFragment deviceExerciseFragment = DeviceExerciseFragment.newInstance();
    private WarmUpExerciseFragment warmUpExerciseFragment = WarmUpExerciseFragment.newInstance();

    /**
     * Creates the Activity.
     * @param savedInstanceState Saved instance State is not Used from us.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> updateExc());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        getNecessaryData(intent.getStringExtra("excName"));

        makeConnections();
    }

    /**
     * Gets the necessary form the Model and the intent information.
     * Afterwards the Activity should be decorated.
     * @param excName
     */
    private void getNecessaryData(String excName) {
        Schedule sched=null;

        sched = ((ApplicationManager)getApplication()).getApplicationModel().activeSchedule();

        Exercise exercise=null;
        if(!excName.isEmpty()) {
            if (sched != null) {
                for (Exercise exc : sched.exercises()) {
                    if (exc.getName().equals(excName)) {
                        exercise = exc;
                    }
                }
            }
        }
        ViewModel=new ExerciseViewModel(sched,exercise);
        if(exercise!=null){
            ((EditText)findViewById(R.id.excName)).setText(exercise.getName());
        }else{
            ((EditText)findViewById(R.id.excName)).setText("");
        }
        ViewModel.getExerciseTypeLiveData().observe(this, observer -> {
            updateGui();
        });

        deviceExerciseFragment.setExerciseViewModel(ViewModel);
        bodyWeightExerciseFragment.setExerciseViewModel(ViewModel);
        warmUpExerciseFragment.setExerciseViewModel(ViewModel);
    }

    /**
     * Makes the necessary connections to the ViewModel.
     */
    private void makeConnections(){
        ((Spinner)findViewById(R.id.ExerciseTypeSpinner)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ViewModel.typeChanged((int)id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ((EditText)findViewById(R.id.excName)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                ViewModel.ExerciseName.postValue(((EditText)findViewById(R.id.excName)).getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ViewModel.ExerciseName.postValue(((EditText)findViewById(R.id.excName)).getText().toString());
            }
        });
    }

    /**
     * Refreshes the Fragment if the Spinner value Changes.
     */
    private void updateGui(){
        switch (ViewModel.getExerciseType()){
            case WarmUp:
                getSupportFragmentManager().beginTransaction().replace(R.id.ExerciseFragment, warmUpExerciseFragment ).commit();
                break;
                default:
            case Device:
                getSupportFragmentManager().beginTransaction().replace(R.id.ExerciseFragment, deviceExerciseFragment ).commit();
                break;
            case BodyWeight:
                getSupportFragmentManager().beginTransaction().replace(R.id.ExerciseFragment, bodyWeightExerciseFragment ).commit();
                break;
        }
    }

    /**
     * Is called if the Exercise is Updated or created
     */
    private void updateExc(){
        if(((EditText)findViewById(R.id.excName)).getText().toString().isEmpty()){
            ((EditText)findViewById(R.id.excName)).setError(getString(R.string.NoNameError));
            return;
        }
        ViewModel.addExercise();
        ((ApplicationManager)getApplication()).saveFile();
        finish();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
