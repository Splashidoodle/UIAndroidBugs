package com.german_software_engineers.trainerapp.ExerciseView.Fragments;

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

import android.net.Uri;
import android.support.v4.app.Fragment;

import com.german_software_engineers.trainerapp.ExerciseView.ViewModel.ExerciseViewModel;

abstract public class ExerciseFragment extends Fragment {

    private ExerciseViewModel ViewModel=null;

    public void setViewModel(ExerciseViewModel viewModel){
        if(viewModel==null)
            throw new NullPointerException();

        ViewModel=viewModel;
    }

    protected ExerciseViewModel viewModel(){
        return ViewModel;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
