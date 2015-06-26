package com.isme.opengl.game2d;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * 绘制线程
 * @author tanyi_000
 * 上午2:17:47  2015-6-27
 */
@SuppressLint("WrongCall")
public class DrawThread extends Thread {

	My2DSurfaceView gameView; //父界面引用
	SurfaceHolder surfaceHolder; 
	private boolean flag = true; //线程工作标志位
	private int sleepSpan = 100; //线程休眠时间
	public DrawThread(My2DSurfaceView gameView) {
		super();
		this.gameView = gameView;
		surfaceHolder = gameView.getHolder(); //创建surfaceHolder对象
	}
	
	
	public void run() {
		Canvas c; //声明画布
		while(this.flag) { //循环执行帧任务
			c = null;
			try {
				c = this.surfaceHolder.lockCanvas(null); //锁定画布
				synchronized (this.surfaceHolder) {
					gameView.onDraw(c);
				}
			} finally {
				if(c != null) {
					this.surfaceHolder.unlockCanvasAndPost(c); //释放锁
				}
			}
			try {
				Thread.sleep(sleepSpan);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}
