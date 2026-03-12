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
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view,
                "Contact us on GitHub", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        makeConnections();
    }

    private void makeConnections(){
        Button LicenseButton = findViewById(R.id.OurLicenseButton);
        LicenseButton.setOnClickListener(listener->{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gnu.org/licenses/gpl-3.0-standalone.html"));
            startActivity(browserIntent);
        });

        Button GithubButton = findViewById(R.id.OpenGithubButton);
        GithubButton.setOnClickListener(listener->{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/TheMHMoritz3/Trainer-App"));
            startActivity(browserIntent);
        });

        Button SeeOtherLicenses = findViewById(R.id.OtherLicensesButton);
        SeeOtherLicenses.setOnClickListener(listener->{
            startActivity(new Intent(this, OssLicensesMenuActivity.class));
        });
    }
}
