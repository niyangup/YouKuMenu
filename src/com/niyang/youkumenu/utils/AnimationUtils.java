package com.niyang.youkumenu.utils;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.RelativeLayout;

public class AnimationUtils {
	public static int running=0;
	public static void RotateOutDisplay(RelativeLayout layout,long delay) {
		RotateAnimation ra = new RotateAnimation(0, -180,
					Animation.RELATIVE_TO_SELF, 0.5f, 
					Animation.RELATIVE_TO_SELF, 1f);
		ra.setDuration(500);
		ra.setFillAfter(true);
		ra.setStartOffset(delay);
		ra.setAnimationListener(new MyAnimationListener());
		
		layout.startAnimation(ra);
	}
	
	public static void RotateInDisplay(RelativeLayout layout,long delay) {
		RotateAnimation ra = new RotateAnimation(-180, 0,
					Animation.RELATIVE_TO_SELF, 0.5f, 
					Animation.RELATIVE_TO_SELF, 1f);
		ra.setDuration(500);
		ra.setStartOffset(delay);
		ra.setFillAfter(true);
		ra.setAnimationListener(new MyAnimationListener());
		
		layout.startAnimation(ra);
	}
	
	static class MyAnimationListener implements AnimationListener{

		@Override
		public void onAnimationStart(Animation animation) {
			running++;
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			running--;
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			
		}
		
	}

}
