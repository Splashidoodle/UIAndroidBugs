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

public abstract class Exercise {
    private String Name;
    private int Position = Integer.MAX_VALUE;

    private Exercise(){}

    public Exercise(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }


    @Override
    public String toString()
    {
        return Name;
    }

    @Override
    public boolean equals( Object obj )
    {
        return  (toString()==obj.toString());
    }

    public abstract ExerciseType type();

    public int getPosition() {
        return Position;
    }

    public void setPosition(int position) {
        Position = position;
    }
}
