package com.example.sergio.challenge.presentation.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View.MeasureSpec;
import android.view.WindowManager;

public class Screen {
    public static float density = 1.0F;
    public static DisplayMetrics displayMetrics = new DisplayMetrics();
    public static Point displaySize = new Point();
    private static Screen instance;
    private final String TAG = Screen.class.getSimpleName();

    private Screen(Context context) {
        this.checkDisplaySize(context, (Configuration) null);
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new Screen(context);
        }

    }

    public static int dp(float value) {
        return value == 0.0F ? 0 : (int) Math.ceil((double) (density * value));
    }

    public static int dp2(float value) {
        return value == 0.0F ? 0 : (int) Math.floor((double) (density * value));
    }

    public static float dpf2(float value) {
        return value == 0.0F ? 0.0F : density * value;
    }

    public static int nodp(int value) {
        return value == 0 ? 0 : (int) Math.ceil((double) ((float) value / density));
    }

    public static int dp2px(float dpValue) {
        return (int) (dpValue * density + 0.5F);
    }

    public static int reconcileSize(int contentSize, int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case -2147483648:
                if (contentSize < specSize) {
                    return contentSize;
                }

                return specSize;
            case 0:
            default:
                return contentSize;
            case 1073741824:
                return specSize;
        }
    }

    public static float getPixelsInCM(float cm, boolean isX) {
        return cm / 2.54F * (isX ? displayMetrics.xdpi : displayMetrics.ydpi);
    }

    public static int getSideCoord(boolean isX, int side, float p, int sideSize) {
        int total;
        if (isX) {
            total = displaySize.x - sideSize;
        } else {
            total = displaySize.y - sideSize - dp(56.0F);
        }

        int result;
        if (side == 0) {
            result = dp(10.0F);
        } else if (side == 1) {
            result = total - dp(10.0F);
        } else {
            result = Math.round((float) (total - dp(20.0F)) * p) + dp(10.0F);
        }

        if (!isX) {
            result += dp(56.0F);
        }

        return result;
    }

    private void checkDisplaySize(Context context, Configuration newConfiguration) {
        try {
            density = context.getResources().getDisplayMetrics().density;
            Configuration configuration = newConfiguration;
            if (newConfiguration == null) {
                configuration = context.getResources().getConfiguration();
            }

            @SuppressLint("WrongConstant") WindowManager manager = (WindowManager) context.getSystemService("window");
            if (manager != null) {
                Display display = manager.getDefaultDisplay();
                if (display != null) {
                    display.getMetrics(displayMetrics);
                    display.getSize(displaySize);
                }
            }

            int newSize;
            if (configuration.screenWidthDp != 0) {
                newSize = (int) Math.ceil((double) ((float) configuration.screenWidthDp * density));
                if (Math.abs(displaySize.x - newSize) > 3) {
                    displaySize.x = newSize;
                }
            }

            if (configuration.screenHeightDp != 0) {
                newSize = (int) Math.ceil((double) ((float) configuration.screenHeightDp * density));
                if (Math.abs(displaySize.y - newSize) > 3) {
                    displaySize.y = newSize;
                }
            }

            newSize = context.getResources().getDisplayMetrics().densityDpi;
            Log.i(this.TAG, "checkDisplaySize: DPI: " + newSize + " DISPLAY: " + displaySize.x + "x" + displaySize.y);
        } catch (Exception var6) {
            ;
        }

    }

}
