package com.german_software_engineers.trainerapp.Controller;

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

import com.german_software_engineers.trainerapp.TrainingsSchedule;
import com.german_software_engineers.trainerappmodel.Model.Model;
import com.german_software_engineers.trainerappmodel.Schedule.Schedule;

public class ScheduleListModelController {
    private TrainingsSchedule TrainignsScheduleActivity;
    private Model ApplicationModel;

    /**
     * Constructor
     * @param activity Activity to invalidate
     * @param model Model to perform Actions
     */
    public ScheduleListModelController(TrainingsSchedule activity, Model model){
        TrainignsScheduleActivity = activity;
        ApplicationModel = model;
    }

    /**
     * Deletes the Schedule with the given Name
     * @param scheduleName Name of the Schedule
     */
    public void onDeleteClicked(String scheduleName){
        ApplicationModel.deleteSchedule(scheduleName);
        TrainignsScheduleActivity.refreshSchedulesList();
    }

    /**
     * Opens the Edit dialog and Activates the given Schedule
     * @param activeSchedule Schedule to edit
     */
    public void onEditScheduleClicked(Schedule activeSchedule){
        ApplicationModel.setActiveSchedule(activeSchedule);
        TrainignsScheduleActivity.editTrainingsSchedule();
    }
}
