package com.isme.opengl.bluetooth;


import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.isme.opengl.R;

/**
 * 蓝牙搜索列表
 * @author tanyi_000
 * 下午9:11:55  2015-6-29
 */
public class DevidesSearchActivity extends Activity {

	
	private BluetoothAdapter adapter;
	
	private ArrayAdapter<String> adapterPaired;
	private ArrayAdapter<String> adapterNew;
	private ListView lvPaired;
	private ListView lvNew;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_devices_search);
		
		initUI();
		
		adapter = BluetoothAdapter.getDefaultAdapter();
		
		adapterPaired = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		adapterNew = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		lvPaired.setAdapter(adapterPaired);
		lvNew.setAdapter(adapterNew);
		
		regist();
		
		Set<BluetoothDevice> pairedDevices = adapter.getBondedDevices(); //获取以配对的设备
		if(pairedDevices.size() > 0) {
			for(BluetoothDevice device : pairedDevices) {
				adapterPaired.add(device.getName() +"\n"+ device.getAddress());
			} 
		} else {
			adapterPaired.add("没有以配对设备");
		}
		
	}

	/**
	 * 注册广播
	 */
	private void regist() {
		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND); //注册搜索广播
		this.registerReceiver(receiver, filter);
		
		filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED); //注册搜索结束广播
		this.registerReceiver(receiver, filter);
	}

	private void initUI() {
		lvPaired = (ListView) findViewById(R.id.lv_paired);
		lvNew = (ListView) findViewById(R.id.lv_new);
	}
	
	
	/**
	 * 搜索广播
	 */
	private final BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(BluetoothDevice.ACTION_FOUND.equals(action)) { //找到了设备
				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				
				if(device.getBondState() != BluetoothDevice.BOND_BONDED) { //如果没有配对，加入到新设备列表
					adapterNew.add(device.getName() +"\n"+device.getAddress());
				}
			} else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) { //搜索完之后
				//改变标题
				setProgressBarIndeterminateVisibility(false);
				setTitle("search done");
				if(adapterNew.getCount() == 0) {
					adapterNew.add("没有找到设备");
				}
			}
		}
		
	};
	
}
