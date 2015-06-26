"音效
	即时音效 ：短暂、可以重复、可以同时播放
	背景音效
2种音效实现是不同的

"即时音效
SoundPool，先将声音加载到内存中，几乎没有时延，声音长度应该小于7s
声音文件放在 res/raw下
assets : 放置程序使用的一些外部资源，音频、视频、数据文件。getResources().getAssets().open("aa.mp3");

降低采样率来减小文件大小 16kbit/s   立体声-->单声道

"SoundPool使用
	首先初始化 new 对象
	然后加载 一般使用hashmap<int, Integer>   load 返回一个int
	播放音频 (涉及到音量的大小)

	//音量
		am = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
		float currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);//获取当前音量
		volumeMax = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//获取系统最大音量

		//设置系统音量绝对值
		int current = (int)(seekBar.getProgress() * volumeMax / 100); //seekBar的最大为100
		am.setStreamVolume(AudioManager.STREAM_MUSIC, current, 0);
		
		//设置系统音量步进  +  -
//		if(flag == 0)
//		am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FX_FOCUS_NAVIGATION_UP); //是 UP 吗
//		else if(flag == 1)
//		am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FX_FOCUS_NAVIGATION_UP);

"背景音乐
MediaPlayer

有很多生命周期


首先准备音乐文件，可以放到sd卡里面
创建 MediaPlayer 的对象
设置要播放的资源文件
准备播放
添加监听事件  播放，暂停。。



SharedPreference   Editor

SQLite
execSQL(); //只支持非查询的sql语句，CREATE DELETE INSERT UPDATE
rawQuery(); //执行查询sql

创建或者打开数据库
SQLiteDatabase db = SQLiteDatabase.openDatabase(
	"/data/data/com.isme.opengl/mydb", //数据库所在路径
	null, //游标工厂，默认null
	SQLiteDatabase.OPEN_READWRITE |
	SQLiteDatabase.CREATE_IF_NECESSARY //模式为读写，不存在则创建
);

sql: CREATE TABLE IF NOT EXISTS table_name

"查询
String sql = "SELECT * FROM student WHERE sage>?";
Cursor c = db.rawQuery(sql, new String[]{ "20" });
while(c.moveToNext) {
	String no = c.getString(0); //获取第一列信息
	String name = c.getString(1); //获取第二列信息
	int sage = c.getInt(2);
}
c.close(); //关闭Cursor



ContentProvider 分享数据
ContentResolver



I/O 很重要
	sd卡  手机文件夹  assets文件

File f = new File("/sdcrd/"+ name);
FileInputStream fis = new FileInputStream(f);
byte[] buf = new byte[(int) f.length()];
fis.read(buf); //读入文件
fis.close;
String nr = new String(buf, "utf-8"); //转换成字符串
nr = nr.replaceAll("\\r\\n", "\n");


assets目录
InputStream is = this.getResources().getAssets().open(name);
int ch=0;
ByteArrayOutputStream baos = new ByteArrayOutputStream();
while((ch=is.read()) != -1) {
	baos.write(ch); //读取文件
}

byte[] buf = baos.toByteArray(); //转化为字节数组
baos.close();
is.close();
String nr = new String(buf, "utf-8");
