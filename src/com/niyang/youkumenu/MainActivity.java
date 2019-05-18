package com.niyang.youkumenu;

import com.niyang.youkumenu.utils.AnimationUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener {

	private RelativeLayout rl_leve1;
	private RelativeLayout rl_leve2;
	private RelativeLayout rl_leve3;
	private boolean isShowing3=true;
	private boolean isShowing2=true;
	private boolean isShowing1=true;
	private long delay=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initUI();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.v("TAG", "keycode="+keyCode);
		if (keyCode==KeyEvent.KEYCODE_MENU) {
			if (isShowing3) {
				AnimationUtils.RotateOutDisplay(rl_leve3, 0);
				isShowing3=false;
				delay+=200;
				
				AnimationUtils.RotateOutDisplay(rl_leve2, delay);
				isShowing2=false;
				delay+=200;
				
				AnimationUtils.RotateOutDisplay(rl_leve1, delay);
				isShowing1=false;
			}else if (!isShowing3&&isShowing2&&isShowing1) {
				AnimationUtils.RotateOutDisplay(rl_leve2, delay);
				isShowing2=false;
				delay+=200;
				
				AnimationUtils.RotateOutDisplay(rl_leve1, delay);
				isShowing1=false;
			}else if (isShowing1) {
				AnimationUtils.RotateOutDisplay(rl_leve1, delay);
				isShowing1=false;
			}
		}
		
		
		return true;
	}

	private void initUI() {
		rl_leve1 = (RelativeLayout) findViewById(R.id.rl_leve1);
		rl_leve2 = (RelativeLayout) findViewById(R.id.rl_leve2);
		rl_leve3 = (RelativeLayout) findViewById(R.id.rl_leve3);

		ImageButton ib_home = (ImageButton) findViewById(R.id.ib_home);
		ImageButton ib_menu = (ImageButton) findViewById(R.id.ib_menu);

		ib_home.setOnClickListener(this);
		ib_menu.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (AnimationUtils.running>0) {
			return ;
		}
		Log.v("TAG", "点击了:btn");
		switch (v.getId()) {
		case R.id.ib_home:
			Log.v("TAG", "点击了:home");
			
			if (!isShowing2&&!isShowing3) {
				AnimationUtils.RotateInDisplay(rl_leve2,delay);
				delay+=200;
				AnimationUtils.RotateInDisplay(rl_leve3,delay);
				isShowing2=true;
				isShowing3=true;
				return;
			}
			
			if (isShowing3&&isShowing2) {
				//若在则转出去
				AnimationUtils.RotateOutDisplay(rl_leve3,0);
				isShowing3=false;
				delay=200;
				AnimationUtils.RotateOutDisplay(rl_leve2,delay);
				isShowing2=false;
				return;
			}
			
			if (!isShowing3&&isShowing2) {
				AnimationUtils.RotateOutDisplay(rl_leve2,0);
				isShowing2=false;
				return;
			}
			
				
			break;

		case R.id.ib_menu:
			Log.v("TAG", "点击了:menu");
			
			
			showOrNo3();
			
			break;

		}
	}

	public void showOrNo3() {
		if (isShowing3) {
			//若在则转出去
			AnimationUtils.RotateOutDisplay(rl_leve3,0);
			isShowing3=false;
		}else {
			//若不在则转进来
			AnimationUtils.RotateInDisplay(rl_leve3,delay);
			isShowing3=true;
		}
	}

}
