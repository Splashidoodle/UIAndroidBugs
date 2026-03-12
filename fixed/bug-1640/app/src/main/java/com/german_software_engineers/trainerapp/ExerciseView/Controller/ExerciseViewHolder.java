package com.german_software_engineers.trainerapp.ExerciseView.Controller;

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

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.german_software_engineers.trainerapp.Controller.ExerciseListModelController;
import com.german_software_engineers.trainerapp.ExerciseView.ViewModel.ExerciseViewModel;
import com.german_software_engineers.trainerapp.R;
import com.german_software_engineers.trainerappmodel.Exercise.BodyWeightExercise;
import com.german_software_engineers.trainerappmodel.Exercise.DeviceExercise;
import com.german_software_engineers.trainerappmodel.Exercise.Exercise;
import com.german_software_engineers.trainerappmodel.Exercise.WarmUpExercise;

/**
 * {@Link RecyclerView.ViewHolder} to Display the {@Link Exercise}
 * Decorates it self to Display the Exercises in the RecyclerView
 */
public class ExerciseViewHolder extends RecyclerView.ViewHolder {
    private View ExerciseView;
    private Exercise Exercise;
    private ExerciseListModelController Controller;
    private Toolbar toolbar;
    /**
     * Constructor
     * @param view defined by the super-class
     */
    public ExerciseViewHolder(View view){
        super(view);
        ExerciseView = view;
    }

    /**
     * Sets the exercise and decorates the tile
     * @param exercise the given Exercise
     */
    public void setExercise(Exercise exercise){
        Exercise = exercise;
        decorateExerciseTile();
    }

    private void decorateExerciseTile(){
        toolbar=ExerciseView.findViewById(R.id.ExerciseCardToolbar);
        toolbar.inflateMenu(R.menu.exercise_fragmet_menu);
        switch(Exercise.type()){
            case Device:
                default:
                    decorateDeviceExercise();
                break;
            case WarmUp:
                decorateWarmUpExercise();
                break;
            case BodyWeight:
                decorateBodyWeightExercise();
                break;

        }
    }

    private void decorateDeviceExercise(){
        DeviceExercise exc = (DeviceExercise) Exercise;
        StringBuilder titleBuilder = new StringBuilder();
        titleBuilder.append(exc.getPosition());
        titleBuilder.append("\t\t");
        titleBuilder.append(exc.getName());

        if(exc.isDeviceNumberActivated()){
            titleBuilder.append("\t\t");
            titleBuilder.append(exc.getDeviceNumber());
        }

        ((TextView)ExerciseView.findViewById(R.id.ExerciseTitle)).setText(titleBuilder.toString());


        StringBuilder builder = new StringBuilder();

        if(exc.isWeightActivated())
        {
            if(exc.isAdditionalWeightActivated()) {
                builder.append(ExerciseView.getResources().getString(
                        R.string.DeviceExerciseInfoWeight, exc.getWeight()));
                builder.append(ExerciseView.getResources().getString(
                        R.string.DeviceExerciseInfoAdditionalWeight, exc.getAdditionalWeight()));
            }else{
                builder.append(ExerciseView.getResources().getString(
                        R.string.DeviceExerciseInfoWeight, exc.getWeight()));
                builder.append(ExerciseView.getResources().getString(R.string.LineBreak));
            }
        }

        if(exc.isSeatActivated())
            builder.append(ExerciseView.getResources().getString(R.string.DeviceExerciseInfoSeatPosition,exc.getSeatPosition()));

        if(exc.isLegActivated())
            builder.append(ExerciseView.getResources().getString(R.string.DeviceExerciseInfoLegPosition,exc.getLegPosition()));

        if(exc.isFootActivated())
            builder.append(ExerciseView.getResources().getString(R.string.DeviceExerciseInfoFootPosition,exc.getFootPosition()));

        if(exc.isAngleActivated())
            builder.append(ExerciseView.getResources().getString(R.string.DeviceExerciseInfoAnglePosition, exc.getAnglePosition()));

        if(exc.isBackActivated())
            builder.append(ExerciseView.getResources().getString(R.string.DeviceExerciseInfoBackPosition, exc.getBackPosition()));

        ((TextView)ExerciseView.findViewById(R.id.ExerciseInformation)).setText(builder.toString());
    }

    private void decorateWarmUpExercise(){
        CardView card = ExerciseView.findViewById(R.id.ExerciseCard);
        card.setCardBackgroundColor(ExerciseView.getResources().getColor(R.color.WarmUpColor));
        WarmUpExercise exc = (WarmUpExercise)Exercise;
        StringBuilder titleBuilder = new StringBuilder();
        titleBuilder.append(exc.getPosition());
        titleBuilder.append("\t\t");
        titleBuilder.append(exc.getName());
        ((TextView)ExerciseView.findViewById(R.id.ExerciseTitle)).setText(titleBuilder.toString());

        StringBuilder builder = new StringBuilder();

        if(exc.isExecutionTimeActivated())
            builder.append(ExerciseView.getResources().getString(
                    R.string.WarmUpExerciseInfoExecutionTime,exc.getExecutionTime()));

        if(exc.isExecutionTimeActivated())
            builder.append(ExerciseView.getResources().getString(
                    R.string.WarmUpExerciseInfoIntensity,exc.getIntenity().name()));

        if(exc.isSubintensityActivated())
            builder.append(ExerciseView.getResources().getString(
                    R.string.WarmUpExerciseInfoSubIntensity,exc.getSubIntensity()));

        if(exc.isIntensityActivated())
            builder.append(ExerciseView.getResources().getString(
                    R.string.WarmUpExerciseInfoBPM, exc.getBPM()));

        ((TextView)ExerciseView.findViewById(R.id.ExerciseInformation)).setText(builder.toString());
    }

    private void decorateBodyWeightExercise(){
        BodyWeightExercise exc = (BodyWeightExercise)Exercise;

        StringBuilder titleBuilder = new StringBuilder();
        titleBuilder.append(exc.getPosition());
        titleBuilder.append("\t\t");
        titleBuilder.append(exc.getName());
        ((TextView)ExerciseView.findViewById(R.id.ExerciseTitle)).setText(titleBuilder.toString());

        StringBuilder builder = new StringBuilder();

        if(exc.isAdditionalInformationActivated())
            builder.append(exc.getAdditionalInformation());

        ((TextView)ExerciseView.findViewById(R.id.ExerciseInformation)).setText(builder.toString());
    }

    /**
     * Gives the view of the Exercise
     * @return The view tile
     */
    public View getExerciseView(){
        return ExerciseView;
    }

    /**
     * Gives the Exercise to perform Operations on them.
     * @return The Exercise with Operation
     */
    public Exercise getExercise() {
        return Exercise;
    }

    public void setController(ExerciseListModelController controller) {
        Controller = controller;
        toolbar.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.MoveExerciseDown:
                    Controller.moveExerciseDown(Exercise.getPosition());
                    return true;
                case R.id.MoveExerciseUp:
                    Controller.moveExerciseUp(Exercise.getPosition());
                    return true;
                case R.id.DeleteExercise:
                    Controller.deleteExercise(Exercise.getPosition());
                default:
                    return true;
            }
        });
    }
}
