package com.androiddevclub.autobutton;

import java.util.Arrays;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.StateSet;

public class StateDrawable extends LayerDrawable {

	private Bundle overlayColors;
	
	public static String ENABLED_PRESSED_OVERLAY_COLOR = "ENABLED_PRESSED_OVERLAY_COLOR";
	public static String ENABLED_FOCUSED_OVERLAY_COLOR = "ENABLED_FOCUSED_OVERLAY_COLOR";
	public static String DISABLED_OVERLAY_COLOR = "DISABLED_OVERLAY_COLOR";
	
	public static String DEFAULT_ENABLED_PRESSED_OVERLAY_COLOR = "#55000000";
	public static String DEFAULT_ENABLED_FOCUSED_OVERLAY_COLOR = "#33FFFFFF";
	public static String DEFAULT_DISABLED_OVERLAY_COLOR = "#88444444";
	
	public StateDrawable(Drawable[] layers, Bundle overlayColors) {
		super(layers);
		this.overlayColors = overlayColors;
	}
	
	public StateDrawable(Drawable[] layers) {
		super(layers);
		this.overlayColors = new Bundle();
	}

	@Override
	protected boolean onStateChange(int[] states) {

		if (StateSet.stateSetMatches(
				new int[] { android.R.attr.state_pressed,
						android.R.attr.state_enabled }, states)) {
			super.mutate().setColorFilter(overlayColors.getInt(ENABLED_PRESSED_OVERLAY_COLOR,
					Color.parseColor(DEFAULT_ENABLED_PRESSED_OVERLAY_COLOR)),
					PorterDuff.Mode.SRC_ATOP);
		}
		else if (StateSet.stateSetMatches(
				new int[] { android.R.attr.state_focused,
						android.R.attr.state_enabled }, states)) {
			super.mutate().setColorFilter(overlayColors.getInt(ENABLED_FOCUSED_OVERLAY_COLOR,
					Color.parseColor(DEFAULT_ENABLED_FOCUSED_OVERLAY_COLOR)),
					PorterDuff.Mode.SRC_ATOP);
		}
		else if (Arrays.binarySearch(states, android.R.attr.state_enabled) < 0) {
			super.mutate().setColorFilter(overlayColors.getInt(DISABLED_OVERLAY_COLOR,
					Color.parseColor(DEFAULT_DISABLED_OVERLAY_COLOR)),
					PorterDuff.Mode.SRC_ATOP);
		}
		else {
			super.mutate().clearColorFilter();
		}

		invalidateSelf();

		return super.onStateChange(states);
	}

	@Override
	public boolean isStateful() {
		return true;
	}

}