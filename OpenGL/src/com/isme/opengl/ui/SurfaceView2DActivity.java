package com.isme.opengl.ui;

import com.isme.opengl.game2d.My2DSurfaceView;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * 展示一款炮弹射击，然后爆炸
 * @author tanyi_000
 * 上午1:16:44  2015-6-27
 */
public class SurfaceView2DActivity extends Activity {

	private My2DSurfaceView gameView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE); //全屏模式，取消标题栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN); //去掉状态栏
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //横屏
		
		gameView = new My2DSurfaceView(this);
		setContentView(gameView);
	}
	
}
