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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Basic example for unbundled UiAutomator.
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class Script1202Feature2 {

    private static final String BASIC_SAMPLE_PACKAGE
            = "swati4star.createpdf";
    private static final String AndroidOS = "com.android.packageinstaller";
    private static final int LAUNCH_TIMEOUT = 5000;

    private static final String STRING_TO_BE_TYPED = "UiAutomator";

    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        // Launch the blueprint app
        Context context = getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void scriptFeature1202_2() {

        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // Permissions
        UiObject2 allow = mDevice.wait(Until.findObject(By.res(AndroidOS, "permission_allow_button")),2000);
        allow.click();
        allow = mDevice.wait(Until.findObject(By.res(AndroidOS, "permission_allow_button")),2000);
        allow.click();

        //allow = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "button")),2000);
        //allow.click();

        // Create pdf
        UiObject2 create = mDevice.wait(Until.findObject(By.text( "SELECT IMAGES")),2000);
        create.click();

        UiObject2 gallery = mDevice.wait(Until.findObject(By.text("Gallery")),2000);
        gallery.click();

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mDevice.click(175, 575);

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mDevice.click(530, 575);

        UiObject2 done = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "action_done")),2000);
        done.click();

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        mDevice.click(800,1200);
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        /*
        boolean lineOnImage1 = hasBlackLineAt(560, 500, 800);
        System.out.println("Line detected on image 1: " + lineOnImage1);
         */

        UiObject2 next = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE,"nextimageButton")),2000);
        next.click();

        UiObject2 back = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE,"previousImageButton")),2000);
        back.click();

        assertNotNull(mDevice.findObject(By.text("Showing 1 of 2")));


        /*
        boolean lineOnImage2 = hasBlackLineAt(560, 500, 800);
        System.out.println("Line detected on image 2: " + lineOnImage2);
        assertFalse("Bug: brush stroke incorrectly persists to next image", lineOnImage2);
         */

    }


    //realized I didn't need to actually check the brush stroke
    // since the buggy version doesn't let you go to the next image
    // if you save (related to the same bug). This is still available for using colors if needed
    private int[] getPixelAt(int x, int y) {
        File screenshot = new File(
                getInstrumentation().getTargetContext().getFilesDir(),
                "screen.png"
        );

        boolean ok = mDevice.takeScreenshot(screenshot);
        if (!ok) {
            throw new RuntimeException("Screenshot failed");
        }

        Bitmap bitmap = BitmapFactory.decodeFile(screenshot.getAbsolutePath());
        if (bitmap == null) {
            throw new RuntimeException("Failed to decode screenshot");
        }

        if (x < 0 || y < 0 || x >= bitmap.getWidth() || y >= bitmap.getHeight()) {
            throw new IllegalArgumentException("Pixel out of bounds");
        }

        int pixel = bitmap.getPixel(x, y);

        return new int[]{
                Color.alpha(pixel),
                Color.red(pixel),
                Color.green(pixel),
                Color.blue(pixel)
        };
    }

    private boolean hasBlackLineAt(int x, int yStart, int yEnd) {
        for (int y = yStart; y <= yEnd; y += 20) {
            int[] argb = getPixelAt(x, y);
            System.out.println("Pixel @ (" + x + "," + y + ") ARGB = "
                    + argb[0] + ", " + argb[1] + ", " + argb[2] + ", " + argb[3]);
            if (argb[1] < 30 && argb[2] < 30 && argb[3] < 30) {
                return true;
            }
        }
        return false;
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
