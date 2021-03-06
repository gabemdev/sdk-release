package com.tune.ma.profile;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;

import java.util.Calendar;

public class SystemInfo {

    private final String device;
    private final String model;
    private final int apiLevel;
    private int appBuild;
    private final String tabletOrPhone;
    private static final String TABLET = "tablet";
    private static final String PHONE = "phone";
    private String packageName;

    public String tabletOrPhone() {
        return tabletOrPhone;
    }

    public SystemInfo(Context context) {
        this.device = android.os.Build.DEVICE;
        this.model = android.os.Build.MODEL;
        this.apiLevel = android.os.Build.VERSION.SDK_INT;
        this.tabletOrPhone = isTablet(context) ? TABLET : PHONE;
        
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            this.packageName = context.getPackageName();
            this.appBuild = pInfo.versionCode;
        } catch (NameNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    protected int calculateMinutesFromGMTForCalendar(Calendar calendar) {
        return (calendar.get(Calendar.ZONE_OFFSET) + calendar.get(Calendar.DST_OFFSET)) / 60000;
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public String getDevice() {
        return device;
    }

    public String getModel() {
        return model;
    }

    public String getApiLevel() {
        return "" + apiLevel;
    }

    public String getTabletOrPhone() {
        return tabletOrPhone;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getAppBuild() {
        return Integer.toString(appBuild);
    }
}