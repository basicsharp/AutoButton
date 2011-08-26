package com.androiddevclub.autobutton;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class AutoImageButton extends ImageButton{
	
	public AutoImageButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		createStateDrawables();
	}

	public AutoImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		createStateDrawables();
	}

	public AutoImageButton(Context context) {
		super(context);
		createStateDrawables();
	}
	
	protected void createStateDrawables() {
		if (getBackground() != null && !getBackground().isStateful()) {
			StateDrawable background = new StateDrawable(new Drawable[]{getBackground()});
			setBackgroundDrawable(background);
			
			if (getDrawable() != null && !getDrawable().isStateful()) {
				StateDrawable drawable = new StateDrawable(new Drawable[]{getDrawable()});
				setImageDrawable(drawable);
			}
		}
	}
	
}
