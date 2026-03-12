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
import com.german_software_engineers.trainerappmodel.Enumerations.Intensities;

public class WarmUpExercise extends Exercise {
    private ExerciseType Type;
    private boolean IsExecutionTimeActivated = false;
    private int ExecutionTime=Integer.MAX_VALUE;
    private boolean IsIntensityActivated = false;
    private Intensities Intenity=Intensities.invalid;
    private boolean IsSubintensityActivated;
    private int SubIntensity=Integer.MAX_VALUE;
    private boolean IsBPMActivated = false;
    private int BPM=Integer.MAX_VALUE;

    public WarmUpExercise(String name){
        super(name);
        Type = ExerciseType.WarmUp;
    }

    public ExerciseType type(){
        return Type;
    }


    public int getExecutionTime() {
        if(IsExecutionTimeActivated)
            return ExecutionTime;
        return Integer.MAX_VALUE;
    }

    public void setExecutionTime(int executionTime) {
        if(IsExecutionTimeActivated)
            ExecutionTime = executionTime;
    }

    public Intensities getIntenity() {
        if(IsIntensityActivated)
            return Intenity;
        return Intensities.invalid;
    }

    public void setIntenity(Intensities intenity) {
        if(IsIntensityActivated)
            Intenity = intenity;
    }

    public void setSubIntensity(int subIntensity){
        if(IsSubintensityActivated)
            SubIntensity=subIntensity;
    }

    public int getSubIntensity() {
        if(IsSubintensityActivated)
            return SubIntensity;
        return Integer.MAX_VALUE;
    }

    public int getBPM() {
        if(IsBPMActivated)
            return BPM;
        return Integer.MAX_VALUE;
    }

    public void setBPM(int BPM) {
        if(IsBPMActivated)
            this.BPM = BPM;
    }

    public boolean isExecutionTimeActivated() {
        return IsExecutionTimeActivated;
    }

    public void setExecutionTimeActivated(boolean executionTimeActivated) {
        IsExecutionTimeActivated = executionTimeActivated;
    }

    public boolean isIntensityActivated() {
        return IsIntensityActivated;
    }

    public void setIntensityActivated(boolean intensityActivated) {
        IsIntensityActivated = intensityActivated;
    }

    public boolean isSubintensityActivated() {
        return IsSubintensityActivated;
    }

    public void setSubintensityActivated(boolean subintensityActivated) {
        IsSubintensityActivated = subintensityActivated;
    }

    public boolean isBPMActivated() {
        return IsBPMActivated;
    }

    public void setBPMActivated(boolean BPMActivated) {
        IsBPMActivated = BPMActivated;
    }
}
