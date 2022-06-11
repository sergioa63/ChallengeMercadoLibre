package com.example.sergio.challenge.presentation.common.utils;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.ViewGroup.MarginLayoutParams;

import androidx.annotation.ColorInt;

import com.google.android.material.snackbar.Snackbar;

public class ViewUtils {
    public static void setMaterialSnackbar(Snackbar snackbar) {
        snackbar.setActionTextColor(-9589067);
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) snackbar.getView().getLayoutParams();
        marginLayoutParams.leftMargin = Screen.dp(16.0F);
        marginLayoutParams.rightMargin = Screen.dp(16.0F);
        marginLayoutParams.bottomMargin = Screen.dp(16.0F);
        snackbar.getView().setPadding(Screen.dp(16.0F), Screen.dp(6.0F), Screen.dp(16.0F), Screen.dp(6.0F));
        snackbar.getView().setBackgroundDrawable(createRoundRectDrawable(10, -16777216));
    }

    public static Drawable createRoundRectDrawable(int rad, @ColorInt int color) {
        return createRoundRectDrawable(rad, rad, rad, rad, color);
    }

    public static Drawable createRoundRectDrawable(int topRightRad, int topLeftRad, int bottomRightRad, int bottomLeftRad, @ColorInt int color) {
        RoundRectShape roundRectShape = new RoundRectShape(new float[]{(float) Screen
                .dp((float) topRightRad), (float) Screen.dp((float) topRightRad), (float) Screen.dp((float) topLeftRad),
                (float) Screen.dp((float) topLeftRad), (float) Screen.dp((float) bottomRightRad), (float) Screen.dp((float) bottomRightRad),
                (float) Screen.dp((float) bottomLeftRad), (float) Screen.dp((float) bottomLeftRad)}, (RectF) null, (float[]) null);
        ShapeDrawable drawable = new ShapeDrawable(roundRectShape);
        drawable.getPaint().setColor(color);
        return drawable;
    }
}
