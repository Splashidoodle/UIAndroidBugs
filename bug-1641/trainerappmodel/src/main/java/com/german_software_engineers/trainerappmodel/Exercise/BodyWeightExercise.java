package com.german_software_engineers.trainerappmodel.Exercise;

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

import com.german_software_engineers.trainerappmodel.Enumerations.ExerciseType;

public class BodyWeightExercise extends Exercise {
    private ExerciseType Type;

    private boolean IsAdditionalInformationActivated;
    private String AdditionalInformation="";

    public BodyWeightExercise(String name){
        super(name);
        Type=ExerciseType.BodyWeight;
    }

    public ExerciseType type(){
        return Type;
    }

    public String getAdditionalInformation() {
        if(IsAdditionalInformationActivated)
            return AdditionalInformation;
        return "";
    }

    public void setAdditionalInformation(String additionalInformation) {
        if(IsAdditionalInformationActivated)
            AdditionalInformation = additionalInformation;
    }

    public boolean isAdditionalInformationActivated() {
        return IsAdditionalInformationActivated;
    }

    public void setAdditionalInformationActivated(boolean additionalInformationActivated) {
        IsAdditionalInformationActivated = additionalInformationActivated;
    }
}
