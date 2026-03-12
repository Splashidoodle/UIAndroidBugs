package com.german_software_engineers.trainerapp;

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
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.german_software_engineers.trainerapp.Controller.ApplicationManager;
import com.german_software_engineers.trainerapp.Controller.ScheduleListModelController;
import com.german_software_engineers.trainerapp.ExerciseView.Activity.ExerciseViewActivity;
import com.german_software_engineers.trainerapp.ScheduleView.GeneralTrainingScheduleEditor;
import com.german_software_engineers.trainerapp.ScheduleView.ScheduleListFragment;
import com.german_software_engineers.trainerappmodel.Schedule.Schedule;

public class TrainingsSchedule extends NavigationActivity implements ScheduleListFragment.OnListFragmentInteractionListener{

    ScheduleListModelController controller;
    ScheduleListFragment editScheduleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainings_schedule);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> openNewTrainingsEditor());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        controller = new ScheduleListModelController(this,((ApplicationManager)getApplication()).getApplicationModel());
        editScheduleFragment = ScheduleListFragment.newInstance(1, controller);
        getSupportFragmentManager().beginTransaction().replace(R.id.ScheduleFragment, editScheduleFragment ).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.trainings_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_schedule) {
            Intent intent = new Intent(this, TrainingsSchedule.class);
            startActivity(intent);
        } else if (id == R.id.nav_info) {
            Intent intent = new Intent(this, InfoActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(Schedule item) {
        ((ApplicationManager)getApplication()).getApplicationModel().setActiveSchedule(item);
        Intent intent = new Intent(this, ExerciseViewActivity.class);
        startActivity(intent);
    }

    public void openNewTrainingsEditor(){
        ((ApplicationManager)getApplication()).getApplicationModel().setActiveSchedule(null);
        Intent intent = new Intent(this, GeneralTrainingScheduleEditor.class);
        startActivity(intent);
    }

    public void refreshSchedulesList(){
        editScheduleFragment.refreshItemList();
        ((ApplicationManager) getApplication()).saveFile();
    }

    public void editTrainingsSchedule(){
        Intent intent = new Intent(this, GeneralTrainingScheduleEditor.class);
        startActivity(intent);
    }
}
