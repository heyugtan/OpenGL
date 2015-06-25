package com.isme.opengl.ui;



import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.isme.opengl.R;

/**
 * 背景音乐
 * @author tanyi_000
 * 下午10:00:25  2015-6-25
 */
public class BackgroundMusicActivity extends Activity implements OnClickListener {

	private Button btnPlay;
	private Button btnPause;
	private Button btnStop;
	
	private MediaPlayer mp;
	
	private AudioManager am;
	private int maxVolume;
	private int currentVolume;//当前音量
	private int stepVolume = 1;//每次调整音量的大小
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_background_music);
		
		initView();
		initVolume();
		initPlayer();
	}

	private void initVolume() {
		am = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
		maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	}

	private void initPlayer() {
		mp = new MediaPlayer();
		try {
			mp.setDataSource("/sdcard/sun_non.mp3");
			mp.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initView() {
		btnPlay = (Button) findViewById(R.id.btn_play);
		btnPause = (Button) findViewById(R.id.btn_pause);
		btnStop = (Button) findViewById(R.id.btn_stop);
		
		btnPlay.setOnClickListener(this);
		btnPause.setOnClickListener(this);
		btnStop.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_play:
			mp.start();
			break;
			
		case R.id.btn_pause:
			mp.pause();
			break;
			
		case R.id.btn_stop:
			mp.stop();
			//调用 prepare 和 prepareAsync 那么，stop没有效果了，只是像暂停
			initPlayer();
			break;

		default:
			break;
		}
	}
	
	public void volume_up(View v) {
		//增大音量，但不能超过最大
		currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		int tempVolume = stepVolume + currentVolume;//临时音量
		currentVolume = tempVolume > maxVolume ? maxVolume : tempVolume;
		am.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, AudioManager.FLAG_PLAY_SOUND);
	}
	
	public void volume_down(View v) {
		currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		int tempVolume = currentVolume - stepVolume;
		currentVolume = tempVolume < 0 ? 0 : tempVolume;
		am.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, AudioManager.FLAG_PLAY_SOUND);
	}
	
}
