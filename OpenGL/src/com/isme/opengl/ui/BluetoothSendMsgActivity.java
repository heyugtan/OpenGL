package com.isme.opengl.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.isme.opengl.R;

/**
 * 通过蓝牙发送消息
 * 查找-选择-匹配-连接-发送
 * 需要2个端
 * @author tanyi_000
 * 下午6:56:58  2015-6-28
 */
public class BluetoothSendMsgActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bluetooth_send_msg);
	}
	
	public void searchDevice(View v) {
		
	}
	
}
