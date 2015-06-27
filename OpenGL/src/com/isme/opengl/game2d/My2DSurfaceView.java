package com.isme.opengl.game2d;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.isme.opengl.R;
import com.isme.opengl.ui.SurfaceView2DActivity;

/**
 * 2D动画，实现一个炮弹放射，然后爆炸
 * @author tanyi_000
 * 上午1:18:46  2015-6-27
 */
public class My2DSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

	private SurfaceView2DActivity activity;
	private Paint paint; //画笔
	private Bitmap bgBitmap; //背景图片
	private Bitmap bulletBitmap; //炸弹位图
	private Bitmap[] explodeBitmap; //爆炸位图
	
	DrawThread drawThread; //绘制线程
	
	private Bullet bullet;
	
	public My2DSurfaceView(SurfaceView2DActivity activity) {
		super(activity);
		this.activity = activity;
		
		this.requestFocus();
		this.setFocusableInTouchMode(true); //可触控
		getHolder().addCallback(this); //注册回调接口
	}

	@Override
	protected void onDraw(Canvas canvas) { //绘制界面
		super.onDraw(canvas);
		canvas.drawBitmap(bgBitmap, 0, 0, paint); //绘制背景
		bullet.drawSelf(canvas, paint); //绘制炮弹
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		paint = new Paint();
		paint.setAntiAlias(true); //打开抗锯齿
		
		bulletBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bullet);
		bgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
		explodeBitmap = new Bitmap[]{
				BitmapFactory.decodeResource(getResources(), R.drawable.explode0),
				BitmapFactory.decodeResource(getResources(), R.drawable.explode2),
				BitmapFactory.decodeResource(getResources(), R.drawable.explode3),
				BitmapFactory.decodeResource(getResources(), R.drawable.explode4),
				BitmapFactory.decodeResource(getResources(), R.drawable.explode5)
		};
		
		bullet = new Bullet(this, bulletBitmap, explodeBitmap, 0, 350, 1.3f, -5.9f); //创建炮弹
		
		drawThread = new DrawThread(this);
		drawThread.start();
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO nothing
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		drawThread.setFlag(false); //停止绘制线程
	}

}
