/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.sage.android;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

/**
 * Basic example for unbundled UiAutomator.
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class Script8Same3 {

    private static final String PACKAGE_NAME
            = "de.koelle.christian.trickytripper";

    private static final int LAUNCH_TIMEOUT = 5000;

    private static final String STRING_TO_BE_TYPED = "UiAutomator";

    private UiDevice uiDevice;

    @Before
    public void startActivityFromHomeScreen() {
        // Initialize UiDevice instance
        uiDevice = UiDevice.getInstance(getInstrumentation());

        // Start from the home screen
        uiDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        uiDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        // Launch the blueprint app
        Context context = getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(PACKAGE_NAME);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        context.startActivity(intent);

        // Wait for the app to appear
        uiDevice.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void scriptSame8_3() throws UiObjectNotFoundException {
        // Setup GUI actions
        // Press ok button
        uiDevice.findObject(new UiSelector().className("android.widget.Button")).click();
        // Press create traveler option
        uiDevice.findObject(By.res(PACKAGE_NAME, "option_add_participant")).click();
        // Press text box
        uiDevice.findObject(new UiSelector().className("android.widget.EditText")).click();
        // Enter "t" in text box
        uiDevice.findObject(new UiSelector().className("android.widget.EditText")).legacySetText("t");
        // Press tick option
        uiDevice.findObject(By.res(PACKAGE_NAME, "option_accept")).click();
        // Press back option
        uiDevice.click(0, 100);

        // Bug-specific GUI actions
        // Long-press "t" item
        uiDevice.findObject(By.res(PACKAGE_NAME,
                "participantTabRowView_output_participantName")).click(1000);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Press menu option
        uiDevice.click(1000, 100);

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Press create payment option
        uiDevice.wait(Until.findObject(By.text("Create payment")), 2000).click();

        UiObject2 two = uiDevice.wait(Until.findObject(By.res("com.google.android.inputmethod.latin:id/key_pos_number_line1_2")),2000);
        two.click();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        uiDevice.click(914, 130);

        assertEquals("2.00", uiDevice.wait(Until.findObject(By.res(PACKAGE_NAME ,"participant_tab_row_view_output_paid")), 2000).getText());


    }

    /**
     * Uses package manager to find the package name of the device launcher. Usually this package
     * is "com.android.launcher" but can be different at times. This is a generic solution which
     * works on all platforms.`
     */
    private String getLauncherPackageName() {
        // Create launcher Intent
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        // Use PackageManager to get the launcher package name
        PackageManager pm = getApplicationContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }
}
