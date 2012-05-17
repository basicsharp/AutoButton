package com.androiddevclub.autobutton;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public class AutoButton extends Button {

	public AutoButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		createStateDrawables();
	}

	public AutoButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		createStateDrawables();
	}

	public AutoButton(Context context) {
		super(context);
		createStateDrawables();
	}

	protected void createStateDrawables() {
		if (getBackground() != null && !getBackground().isStateful()) {
			StateDrawable background = new StateDrawable(new Drawable[]{getBackground()});
			setBackgroundDrawable(background);
			
			Drawable[] compoundDrawables = getCompoundDrawables();
			if (compoundDrawables != null) {
				for (int i = 0; i < compoundDrawables.length; i++) {
					if (compoundDrawables[i] != null
							&& !compoundDrawables[i].isStateful()) {
						StateDrawable drawable = new StateDrawable(new Drawable[]{compoundDrawables[i]});
						drawable.setBounds(compoundDrawables[i].copyBounds());
						compoundDrawables[i] = drawable;
					}
				}
				setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
			}
			
			/*int[] enabledState = new int[]{android.R.attr.state_enabled};
			int[] pressedState = new int[]{android.R.attr.state_pressed, android.R.attr.state};
			
			int currentColor = getTextColors().getDefaultColor();
			
			float[] hsv = new float[3];
			Color.colorToHSV(currentColor, hsv);
			hsv[2] *= 0.1f;
			int darkerColor = Color.HSVToColor(hsv);
			
			ColorStateList colors = new ColorStateList(new int[][]{enabledState, pressedState},
					new int[]{currentColor, darkerColor});
			setTextColor(colors);*/
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		return super.onTouchEvent(event);
	}
	
	
	
}
