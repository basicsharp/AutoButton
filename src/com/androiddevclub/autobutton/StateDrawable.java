package com.androiddevclub.autobutton;

import java.util.Arrays;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.StateSet;

public class StateDrawable extends LayerDrawable {

	private Bundle overlayColors;
	
	public static String ENABLED_PRESSED_OVERLAY_COLOR = "ENABLED_PRESSED_OVERLAY_COLOR";
	public static String ENABLED_FOCUSED_OVERLAY_COLOR = "ENABLED_FOCUSED_OVERLAY_COLOR";
	public static String DISABLED_OVERLAY_COLOR = "DISABLED_OVERLAY_COLOR";
	
	public static String DEFAULT_ENABLED_PRESSED_OVERLAY_COLOR = "#66000000";
	public static String DEFAULT_ENABLED_FOCUSED_OVERLAY_COLOR = "#55FFFFFF";
	public static String DEFAULT_DISABLED_OVERLAY_COLOR = "#88444444";
	
	public StateDrawable(Drawable[] layers, Bundle overlayColors) {
		super(layers);
		this.overlayColors = overlayColors;
	}
	
	public StateDrawable(Drawable[] layers) {
		this(layers, new Bundle());
	}

	@Override
	protected boolean onStateChange(int[] states) {
		
		Drawable drawable = getDrawable(0);
		
		if (StateSet.stateSetMatches(
				new int[] { android.R.attr.state_pressed,
						android.R.attr.state_enabled }, states)) {
			
			if (drawable.getClass() != GradientDrawable.class) {
				super.mutate().setColorFilter(overlayColors.getInt(ENABLED_PRESSED_OVERLAY_COLOR,
						Color.parseColor(DEFAULT_ENABLED_PRESSED_OVERLAY_COLOR)),
						PorterDuff.Mode.SRC_ATOP);
			}
			else {
				PorterDuffColorFilter cf = new PorterDuffColorFilter(overlayColors.getInt(ENABLED_PRESSED_OVERLAY_COLOR,
						Color.parseColor(DEFAULT_ENABLED_PRESSED_OVERLAY_COLOR)),
						PorterDuff.Mode.SRC_ATOP);
				drawable.setColorFilter(cf);
			}
			
		}
		else if (StateSet.stateSetMatches(
				new int[] { android.R.attr.state_focused,
						android.R.attr.state_enabled }, states)) {
			
			if (drawable.getClass() != GradientDrawable.class) {
				super.mutate().setColorFilter(overlayColors.getInt(ENABLED_FOCUSED_OVERLAY_COLOR,
						Color.parseColor(DEFAULT_ENABLED_FOCUSED_OVERLAY_COLOR)),
						PorterDuff.Mode.SRC_ATOP);
			}
			else {
				PorterDuffColorFilter cf = new PorterDuffColorFilter(overlayColors.getInt(ENABLED_FOCUSED_OVERLAY_COLOR,
						Color.parseColor(DEFAULT_ENABLED_FOCUSED_OVERLAY_COLOR)),
						PorterDuff.Mode.SRC_ATOP);
				drawable.setColorFilter(cf);
			}
			
		}
		else if (Arrays.binarySearch(states, android.R.attr.state_enabled) < 0) {
			
			if (drawable.getClass() != GradientDrawable.class) {
				super.mutate().setColorFilter(overlayColors.getInt(DISABLED_OVERLAY_COLOR,
						Color.parseColor(DEFAULT_DISABLED_OVERLAY_COLOR)),
						PorterDuff.Mode.SRC_ATOP);
			}
			else {
				PorterDuffColorFilter cf = new PorterDuffColorFilter(overlayColors.getInt(DISABLED_OVERLAY_COLOR,
						Color.parseColor(DEFAULT_DISABLED_OVERLAY_COLOR)),
						PorterDuff.Mode.SRC_ATOP);
				drawable.setColorFilter(cf);
			}
				
		}
		else {
			
			if (drawable.getClass() != GradientDrawable.class) {
				super.mutate().clearColorFilter();
			}
			else {
				drawable.clearColorFilter();
			}
			
		}

		invalidateSelf();

		return super.onStateChange(states);
	}

	@Override
	public boolean isStateful() {
		return true;
	}

}