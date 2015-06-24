package com.isme.opengl.ui;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.isme.opengl.R;

/**
 * 音频
 * @author tanyi_000
 * 下午8:23:14  2015-6-24
 */
public class RealTimeAudioActivity extends Activity implements OnSeekBarChangeListener {
	
	public static final String TAG = RealTimeAudioActivity.class.getSimpleName();
	
	private SoundPool sp;
	private Map<Integer, Integer> hm;
	
	private SeekBar sbVolume;
	private float volume;
	private AudioManager am;
	private float volumeMax;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_realtime_audio);
		
		hm = new HashMap<Integer, Integer>();
		sp = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		//一开始就加载，否则，在点击才加载的时候，不能播放  not ready
		hm.put(1, sp.load(this, R.raw.shake_match, 1));
		hm.put(2, sp.load(this, R.raw.shake_nomatch, 1));
		hm.put(3, sp.load(this, R.raw.shake_sound_male, 1));
		
		initView();
		initVolume();
	}
	
	private void initView() {
		sbVolume = (SeekBar) findViewById(R.id.sb_volume);
		sbVolume.setMax(100);
		sbVolume.setOnSeekBarChangeListener(this);
	}

	/**
	 * 进入初始化 音量
	 */
	private void initVolume() {
		am = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
		float currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);//获取当前音量
		volumeMax = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//获取系统最大音量
		volume = currentVolume / volumeMax;
		sbVolume.setProgress((int)(100 * volume));
	}

	/**
	* <p> 单个循环 </p>
	* @param view
	 */
	public void single_no_cycle(View view) {
		reset();
		
//		hm.put(1, sp.load(this, R.raw.shake_match, 1));
		
		//把sp初始化和hm放之前加载 这样设置监听之后反而没有声音了
//		sp.setOnLoadCompleteListener(new OnLoadCompleteListener() {
//			@Override
//			public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
//				sp.play(hm.get(1), volume, volume, 1, 0, 1);
//			}
//		});
		sp.play(hm.get(1), volume, volume, 1, 0, 1);
	}
	
	/**
	 * <p> 多个循环 </p>
	 * @param view
	 */
	public void audios_no_cycle(View view) {
		reset();
//		sp = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		
		sp.play(hm.get(1), volume, volume, 1, 0, 1);
		sp.play(hm.get(2), volume, volume, 1, 0, 1);
		sp.play(hm.get(3), volume, volume, 1, 0, 1);
	}
	
	/**
	 * <p> 单个循环 </p>
	 * @param view
	 */
	public void single_cycle(View view) {
		reset();
//		sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		
		sp.play(hm.get(1), volume, volume, 1, -1, 1);
	}
	
	/**
	 * <p> 多个循环 </p>
	 * @param view
	 */
	public void audios_cycle(View view) {
		reset();
//		sp = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		
		sp.play(hm.get(1), volume, volume, 1, -1, 1);
		sp.play(hm.get(2), volume, volume, 1, -1, 1);
		sp.play(hm.get(3), volume, volume, 1, -1, 1);
	}

	/**
	 * 重置 SoundPool 和 Map
	 */
	private void reset() {
//		sp = null;
//		hm.clear();
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		volume = (float)seekBar.getProgress() / 100;
		Log.i(TAG, String.valueOf(seekBar.getProgress()));
		Log.i(TAG, String.valueOf(volume));
		
		//设置系统音量绝对值
		int current = (int)(seekBar.getProgress() * volumeMax / 100); //seekBar的最大为100
		am.setStreamVolume(AudioManager.STREAM_MUSIC, current, 0);
		
		//设置系统音量步进  +  -
//		if(flag == 0)
//		am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FX_FOCUS_NAVIGATION_UP); //是 UP 吗
//		else if(flag == 1)
//		am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FX_FOCUS_NAVIGATION_UP);
	}
	
}
