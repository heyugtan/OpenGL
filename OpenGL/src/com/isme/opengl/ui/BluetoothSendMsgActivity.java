package com.isme.opengl.ui;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.isme.opengl.R;

/**
 * 通过蓝牙发送消息
 * 查找-选择-匹配-连接-发送
 * 需要2个端
 * @author tanyi_000
 * 下午6:56:58  2015-6-28
 */
public class BluetoothSendMsgActivity extends Activity {

	private BluetoothAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bluetooth_send_msg);
		
		adapter = BluetoothAdapter.getDefaultAdapter();
	}
	
	public void searchDevice(View v) {
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		if(! adapter.isEnabled()) { //蓝牙没有开启
			Toast.makeText(this, "请打开蓝牙", 0).show();
			finish(); // 可以进入到设置页面，开启
		} else {
			initChat();
		}
	}

	/**
	 * 初始化Chat组件
	 */
	private void initChat() {
		// TODO Auto-generated method stub
		
	}
	
	
}
