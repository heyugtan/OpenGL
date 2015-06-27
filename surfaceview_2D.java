继承 SurfaceView  实现 surfaceholder的callbac接口

在activity中直接加进去，setcontentView(mySurfaceView);


surfaceview 有3个生命周期
create
changed-屏幕旋转时调用，至少执行一次
destroy


/**
 * 炮弹发射出去，然后爆炸的2D动画，使用surfaceView
 */
重写构造方法 传递Activity对象过来    My2DSurfaceActivity
	注册 holder的回调   getHolder().addCallback(this);

重写 ondraw
	执行背景画布 canvas.drawBitmap(bitmap, 0, 0, paint);
	bullet炸弹绘制 bullet.drawSelf(cavans, paint); //炸弹类

在create中
	创建paint对象，设置
	资源文件加载
	创建炸弹Bullet对象  new Bullet(this, bulletBitmap, explodeBitmaps, 0, 290, 1.3f, -5.9f); //最后是x y位置，和x y的速度
	然后创建绘画线程启动  new DrawThread(this);

炸弹类 Bullet
有 xy位置，xy速度   有一个生存时间 t 间隔时间timeSpan=0.5f，不爆炸则加
平抛运动， a*t*t + v*t + y //加速度为重力 G
炸弹有尺寸，图片的高度

drawSelf(canvas, paint)方法
判断爆炸标志是否 true 爆炸对象是否 null
不是，则更新 x y 并且判断是否需要爆炸，且更新 t
然后绘制炸弹，canvas.drawBitmap();

判断爆炸
	x >= Screen_width || y > some_value
	如果达到条件，创建爆炸对象  Explode   爆炸标志置 true

"爆炸类 Explode
x y的位置
包几张图片，animIndex
drawSelf(canvas, paint);
判断animIndex是否到bitmaps组后 return
否则继续画
canvas.drawBitmap(bitmaps[animIndex], x, y, paint);  animIndex ++;

绘画线程 DrawThread
线程 继承 Thread
需要父界面的引用   My2DSurfaceView gameVeiw;  构造器传递
	SurfaceHolder s = gameView.getHolder();

run() 标志判断是否循环绘画
Canvas c = null;
while(flag) {
	try{
		c = this.surfaceHolder.lockCanvas(null); //锁定画布
		synchronized(this.surfaceHolder) {
			gameView.onDraw(c); //调用父界面的绘画方法，然后一层一层进入 -->Explode
		}
	} finally {
		if(c != null) {
			this.surfaceHolder.unlockCanvasAndPost(c); //释放锁
		}
	}
	Thread.sleep(sleepSpan); //线程睡眠 100ms
}

是否继续循环绘画标志 flag
public void setFlag(flag) {
	this.flag = flag;
}

在surfaceView的 destroy 里面调用  drawThread.setFlag(true);


