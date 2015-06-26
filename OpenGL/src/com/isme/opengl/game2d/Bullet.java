package com.isme.opengl.game2d;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 炮弹
 * @author tanyi_000
 * 上午2:16:01  2015-6-27
 */
public class Bullet {

	private My2DSurfaceView gameView;
	private Bitmap bitmap; //位图
	private Bitmap[] bitmaps; //爆炸动画图组
	private float x; //x轴位置
	private float y; //y轴位置
	private float vx; //x轴速度
	private float vy; //y轴速度
	private float t; //生存时间
	private float timeSpan = 0.5f; //时间间隔
	int size; //炮弹尺寸
	boolean explodeFlag = false; //是否绘制炮弹的标记
	Explosion mExplosion; //爆炸对象
	
	public Bullet(My2DSurfaceView gameView, Bitmap bitmap, Bitmap[] bitmaps,
			float x, float y, float vx, float vy) {
		super();
		this.gameView = gameView;
		this.bitmap = bitmap;
		this.bitmaps = bitmaps;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		
		size = bitmap.getHeight(); //图片高度-炸弹尺寸
	}
	
	
	public void drawSelf(Canvas canvas, Paint paint) { //绘制炮弹
		if(explodeFlag && mExplosion != null) {
			mExplosion.drawSelf(canvas, paint);
		} else {
			go(); //炮弹前进
			canvas.drawBitmap(bitmap, x, y, paint); //绘制炮弹
		}
	}

	
	private void go() {
		//抛物线运动，物理平抛
		x += vx * t; //水平方向匀速运动
		y = y + vy*t + 0.5f * Constant.G * t*t; //竖直上抛   重力G
		
		if(x >= Constant.EXPLOSION_X || y > Constant.SCREEN_HEIGHT) {
			mExplosion = new Explosion(gameView, bitmaps, x, y); //爆炸对象
			explodeFlag = true;
			return;
		}
		t += timeSpan; //更新生存时间
	}
	
	
}
