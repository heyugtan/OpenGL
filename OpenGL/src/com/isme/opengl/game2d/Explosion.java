package com.isme.opengl.game2d;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 爆炸
 * @author tanyi_000
 * 上午2:16:11  2015-6-27
 */
public class Explosion {

	private My2DSurfaceView gameView;
	private Bitmap[] bitmaps;
	float x; //x轴位置
	float y;
	int animIndex = 0; //爆炸动画帧索引
	
	public Explosion(My2DSurfaceView gameView, Bitmap[] bitmaps, float x,
			float y) {
		super();
		this.gameView = gameView;
		this.bitmaps = bitmaps;
		this.x = x;
		this.y = y;
	}
	
	public void drawSelf(Canvas canvas, Paint paint){
		if(animIndex >= bitmaps.length -1) {
			return; //帧动画播放完毕
		}
		canvas.drawBitmap(bitmaps[animIndex], x, y, paint); //绘制数组中某图
		animIndex ++;
	}
	
}
