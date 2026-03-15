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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.RemoteException;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Basic example for unbundled UiAutomator.
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class Script447Same1 {

    private static final String BASIC_SAMPLE_PACKAGE
            = "com.trianguloy.urlchecker";

    private static final int LAUNCH_TIMEOUT = 5000;

    private static final String STRING_TO_BE_TYPED = "UiAutomator";

    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen()  {
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

    String a = "{\n" +
            "  \"Warning! Non-ASCII characters found.\": {\n" +
            "    \"regex\": \"[^\\\\p{ASCII}]\"\n" +
            "  },\n" +
            "  \"Warning! Upper case letter in domain.\": {\n" +
            "    \"regex\": \"^.*?:\\/\\/[^\\/?#]*[A-Z]\"\n" +
            "  },\n" +
            "  \"HTTP link. Consider using HTTPS.\": {\n" +
            "    \"regex\": \"^http:\\/\\/\",\n" +
            "    \"replacement\": \"https:\\/\\/\"\n" +
            "  },\n" +
            "  \"Missing HTTP scheme.\": {\n" +
            "    \"regex\": \"^(?!.*:)\",\n" +
            "    \"replacement\": \"http:\\/\\/$0\"\n" +
            "  },\n" +
            "  \"Missing HTTPS scheme.\": {\n" +
            "    \"regex\": \"^(?!.*:)\",\n" +
            "    \"replacement\": \"https:\\/\\/$0\"\n" +
            "  },\n" +
            "  \"Invalid http scheme capitalization.\": {\n" +
            "    \"regex\": \"^(?!http:)[hH][tT]{2}[pP]:(.*)\",\n" +
            "    \"replacement\": \"http:$1\",\n" +
            "    \"automatic\": \"true\"\n" +
            "  },\n" +
            "  \"Invalid https scheme capitalization.\": {\n" +
            "    \"regex\": \"^(?!https:)[hH][tT]{2}[pP][sS]:(.*)\",\n" +
            "    \"replacement\": \"https:$1\",\n" +
            "    \"automatic\": \"true\"\n" +
            "  },\n" +
            "  \"Reddit ➔ Teddit\": {\n" +
            "    \"regex\": \"^https?:\\/\\/(?:[a-z0-9-]+\\\\.)*?reddit.com\\/(.*)\",\n" +
            "    \"replacement\": \"https:\\/\\/teddit.net\\/$1\",\n" +
            "    \"enabled\": \"true\"\n" +
            "  },\n" +
            "  \"Twitter ➔ Nitter\": {\n" +
            "    \"regex\": \"^https?:\\/\\/(?:[a-z0-9-]+\\\\.)*?twitter.com\\/(.*)\",\n" +
            "    \"replacement\": \"https:\\/\\/nitter.net\\/$1\",\n" +
            "    \"enabled\": \"false\"\n" +
            "  },\n" +
            "  \"Youtube ➔ Invidious\": {\n" +
            "    \"regex\": \"^https?:\\/\\/(?:[a-z0-9-]+\\\\.)*?youtube.com\\/(.*)\",\n" +
            "    \"replacement\": [\n" +
            "      \"https:\\/\\/yewtu.be\\/$1\",\n" +
            "      \"https:\\/\\/farside.link\\/invidious\\/$1\"\n" +
            "    ],\n" +
            "    \"enabled\": \"false\"\n" +
            "  }\n" +
            "}";

    @Test
    public void scriptSame447_1() throws IOException, UiObjectNotFoundException, RemoteException {

        UiObject2 skip = mDevice.wait(Until.findObject(By.text("Skip tutorial")), 2000);
        skip.click();

        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        UiObject2 modules = mDevice.wait(Until.findObject(By.text("Modules")), 2000);
        modules.click();

        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        mDevice.swipe(550, 1700, 550, 1000, 20);

        UiObject2 pattern = mDevice.wait(Until.findObject(By.text("Pattern checker:")), 2000);
        pattern.click();

        UiObject2 edit = mDevice.wait(Until.findObject(By.text("Json editor")), 2000);
        edit.click();

        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        UiObject input = mDevice.findObject(new UiSelector().resourceId("com.trianguloy.urlchecker:id/data"));
        input.click();

        input = mDevice.findObject(new UiSelector().resourceId("com.trianguloy.urlchecker:id/data"));
        input.setText(a);

        mDevice.pressBack();
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        mDevice.pressBack();

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        mDevice.click(890, 1060);

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        mDevice.pressBack();

        mDevice.wait(Until.findObject(By.text("http://www.google.com/?ref=referrer&foo=bar#tag")), 2000).click();

        input = mDevice.findObject(new UiSelector().resourceId("com.trianguloy.urlchecker:id/url"));
        input.click();

        input = mDevice.findObject(new UiSelector().textContains("google.com"));
        input.setText("https://www.reddit.com/");

        UiObject2 ok = mDevice.wait(Until.findObject(By.text("OK")), 2000);

        if(ok != null)
        {
            ok.click();
        }

        mDevice.pressBack();

        assertNotNull(mDevice.wait(Until.findObject(By.text("Reddit ➔ Teddit")), 2000));
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
