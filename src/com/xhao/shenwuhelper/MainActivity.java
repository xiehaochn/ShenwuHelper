package com.xhao.shenwuhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import net.youmi.android.AdManager;
import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		findViews();
		setListener();
		AdManager.getInstance(this).init("a8eac50c1699d92e", "1c679f350342f2c8", false);
		AdView adView = new AdView(this, AdSize.FIT_SCREEN);

		// 获取要嵌入广告条的布局
		LinearLayout adLayout=(LinearLayout)findViewById(R.id.adLayout);

		// 将广告条加入到布局中
		adLayout.addView(adView);
	}
	private Button button_baoshi;
	private Button button_tujian;
	private Button button_xiulian;
	private void findViews() {
		// TODO Auto-generated method stub
		button_tujian=(Button)findViewById(R.id.tujian);
		button_baoshi=(Button)findViewById(R.id.baoshi);
		button_xiulian=(Button)findViewById(R.id.xiulian);
	}
	private void setListener() {
		// TODO Auto-generated method stub
		button_tujian.setOnClickListener(this);
		button_baoshi.setOnClickListener(this);
		button_xiulian.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.tujian:{
			Intent intent=new Intent();
			intent.setClass(MainActivity.this, IllustratedHandbook.class);
			startActivity(intent);
			break;
		}
		case R.id.baoshi:{
			Intent intent =new Intent();
			intent.setClass(MainActivity.this, Gem.class);
			startActivity(intent);
			break;
		}
		case R.id.xiulian:{
			Intent intent =new Intent();
			intent.setClass(MainActivity.this, XiuLian.class);
			startActivity(intent);
			break;
		}
		default:
			break;
		}
	}
	
	
}
