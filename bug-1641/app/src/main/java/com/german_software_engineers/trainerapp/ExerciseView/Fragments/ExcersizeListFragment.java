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

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.german_software_engineers.trainerapp.Controller.ApplicationManager;
import com.german_software_engineers.trainerapp.Controller.ExerciseListModelController;
import com.german_software_engineers.trainerapp.ExerciseView.Controller.MyExcersizeRecyclerViewAdapter;
import com.german_software_engineers.trainerapp.R;
import com.german_software_engineers.trainerappmodel.Exercise.Exercise;
import com.german_software_engineers.trainerappmodel.Exceptions.ScheduleAvailableException;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ExcersizeListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private String ScheduleName;
    private MyExcersizeRecyclerViewAdapter Adapter;
    private static ExerciseListModelController Controller;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ExcersizeListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ExcersizeListFragment newInstance(int columnCount, String scheduleName, ExerciseListModelController controller) {
        ExcersizeListFragment fragment = new ExcersizeListFragment();
        Controller = controller;
        fragment.ScheduleName = scheduleName;
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_excersize_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            try {
                ApplicationManager Manager = (ApplicationManager) getActivity().getApplication();
                Adapter =  new MyExcersizeRecyclerViewAdapter(Manager.getApplicationModel().getSchedule(ScheduleName).exercises(), mListener);
                Adapter.setController(Controller);
                recyclerView.setAdapter(Adapter);
            } catch (ScheduleAvailableException e) {
                e.printStackTrace();
            }
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Exercise item);
    }

    public MyExcersizeRecyclerViewAdapter getAdapter(){
        return Adapter;
    }
}
