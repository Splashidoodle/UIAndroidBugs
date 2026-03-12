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

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import com.german_software_engineers.trainerapp.Controller.ScheduleListModelController;
import com.german_software_engineers.trainerapp.R;
import com.german_software_engineers.trainerappmodel.Schedule.Schedule;
import com.german_software_engineers.trainerapp.ScheduleView.ScheduleListFragment.OnListFragmentInteractionListener;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link com.german_software_engineers.trainerappmodel.Exercise.Exercise} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MyScheduleRecyclerViewAdapter extends RecyclerView.Adapter<MyScheduleRecyclerViewAdapter.ViewHolder> {

    private final List<Schedule> mValues;
    private final OnListFragmentInteractionListener mListener;
    private ScheduleListModelController controller = null;

    public MyScheduleRecyclerViewAdapter(List<Schedule> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_schedule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //holder.mIdView.setText(position);
        holder.mContentView.setText(mValues.get(position).getName());

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.onListFragmentInteraction(holder.mItem);
            }
        });

        holder.Toolbar.setOnClickListener(v->{
            if(mListener!=null)
                mListener.onListFragmentInteraction(holder.mItem);
        });

        holder.Toolbar.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.DeleteSchedule:
                    controller.onDeleteClicked(holder.mItem.getName());
                    return true;
                case R.id.EditSchedule:
                    controller.onEditScheduleClicked(holder.mItem);
                    return true;
                    default:
                        return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /**
     * Sets the necessary controller for editing and deleting of the schedule
     * @param controller Schedule List Controller.
     * @see ScheduleListModelController
     */
    public void setController(ScheduleListModelController controller) {
        this.controller = controller;
    }

    /**
     * Adapts the View for usable Code.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final Toolbar Toolbar;
        public Schedule mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
            Toolbar = (view.findViewById(R.id.ScheduleFragmentToolbar));

            Toolbar.inflateMenu(R.menu.schedule_card_menu);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
