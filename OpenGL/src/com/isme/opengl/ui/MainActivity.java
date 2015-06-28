package com.isme.opengl.ui;

import com.isme.opengl.R;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.Activity;
import android.content.Intent;

/**
* <p>Title: 主体 </p>
* <p>@author and </p>
* <p>@version 1 </p>
* <p>data: 2015-6-24 </p>
 */
public class MainActivity extends Activity implements OnItemClickListener {

	private ListView lvContent;
	private String[] data = {"即时音效", "背景音乐", "2D SurfaceView Game", "通过蓝牙发送消息"};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
        initData();
    }

	private void initData() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
		lvContent.setAdapter(adapter);
	}

	private void initView() {
		lvContent = (ListView) findViewById(R.id.lv_content);
		lvContent.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = null;
		switch (position) {
		case 0:
			intent = new Intent(this, RealTimeAudioActivity.class);
			startActivity(intent);
			break;
		case 1:
			intent = new Intent(this, BackgroundMusicActivity.class);
			startActivity(intent);
			break;
		case 2:
			intent = new Intent(this, SurfaceView2DActivity.class);
			startActivity(intent);
			break;
		case 3:
			intent = new Intent(this, BluetoothSendMsgActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}


}
