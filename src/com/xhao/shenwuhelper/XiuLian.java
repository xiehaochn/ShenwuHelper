package com.xhao.shenwuhelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class XiuLian extends Activity implements OnClickListener {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.xiulian_layout);
		findView();
		setListener();
		initDate();
		}
	private int[] gongjijingyan;
	private int[] qitajingyan;
	private int[] zhongjingyan;
	private void initDate() {
		// TODO Auto-generated method stub
		gongjijingyan=new int[]{209,318,515,826,1288,1938,2815,3960,5415,7224,9435,12096,15256,18968,23285,28262,33954,40421,47720,55915};
		qitajingyan=new int[]{104,159,257,412,644,969,1407,1980,2707,3612,4717,6048,7628,9484,11642,14131,16977,20210,23860,27957};
		zhongjingyan=new int[]{833,1272,2057,3304,5152,7752,11257,15840,21657,28896,37737,48384,61024,75872,93137,113048,135816,161681,190880,223657};
	}

	private void setListener() {
		queding_button.setOnClickListener(this);
		dange_cb.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked==true){
					suoyou_cb.setChecked(false);
				}else{
					suoyou_cb.setChecked(true);
				}
			}	
		});
		suoyou_cb.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked==true){
					dange_cb.setChecked(false);
					gongji_cb.setChecked(false);
					qita_cb.setChecked(false);
				}else{
					dange_cb.setChecked(true);
				}
			}	
		});
		gongji_cb.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked==true){
					suoyou_cb.setChecked(false);
					qita_cb.setChecked(false);
				}else{

				}
			}	
		});
		qita_cb.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked==true){
					suoyou_cb.setChecked(false);
					gongji_cb.setChecked(false);
				}else{

				}
			}	
		});
	}
	private Button queding_button;
	private EditText dangqiandengji_et;
	private EditText mubiaodengji_et;
	private TextView suoxujingyan_tv;
	private CheckBox dange_cb;
	private CheckBox suoyou_cb;
	private CheckBox gongji_cb;
	private CheckBox qita_cb;
	private void findView() {
	queding_button=(Button)findViewById(R.id.queding_xiulian);
	dangqiandengji_et=(EditText)findViewById(R.id.dangqiandengji_input);
	mubiaodengji_et=(EditText)findViewById(R.id.mubiaodengji_input);
	suoxujingyan_tv=(TextView)findViewById(R.id.suoxujingyan_output);
	dange_cb=(CheckBox)findViewById(R.id.cb1);
	suoyou_cb=(CheckBox)findViewById(R.id.cb2);
	gongji_cb=(CheckBox)findViewById(R.id.cb3);
	qita_cb=(CheckBox)findViewById(R.id.cb4);
	}
	private int dangqiandengji;
	private int mubiaodengji;
	private int suoxujingyan;
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.queding_xiulian:{			
			if("".equals(dangqiandengji_et.getText().toString())|"".equals(mubiaodengji_et.getText().toString())){
				Toast.makeText(this, "请输入数值", Toast.LENGTH_SHORT).show();
			}else{
				dangqiandengji=Integer.parseInt(dangqiandengji_et.getText().toString());
				mubiaodengji=Integer.parseInt(mubiaodengji_et.getText().toString());
				suoxujingyan=0;
			if(dangqiandengji>20|mubiaodengji>20|dangqiandengji<0|mubiaodengji<0){
				Toast.makeText(this, "非法输入",Toast.LENGTH_SHORT).show();
			}else{
				try{
			if(dange_cb.isChecked()&gongji_cb.isChecked()){
				//单个人物攻击修炼				
				for(int i=dangqiandengji;i<mubiaodengji;i++){
					suoxujingyan=suoxujingyan+gongjijingyan[i];
				}
				suoxujingyan_tv.setText(suoxujingyan+"");
			}else if(dange_cb.isChecked()&qita_cb.isChecked()){
				//单个其他修炼				
				for(int i=dangqiandengji;i<mubiaodengji;i++){
					suoxujingyan=suoxujingyan+qitajingyan[i];
				}
				suoxujingyan_tv.setText(suoxujingyan+"");
			}else if(suoyou_cb.isChecked()){
				//所有修炼类型			
				for(int i=dangqiandengji;i<mubiaodengji;i++){
					suoxujingyan=suoxujingyan+zhongjingyan[i];
				}
				suoxujingyan_tv.setText(suoxujingyan+"");
			}else{
				Toast.makeText(this, "请选择正确的修炼类型", Toast.LENGTH_SHORT).show();
			}}catch(Exception e){
				Toast.makeText(this, "非法输入",Toast.LENGTH_SHORT).show();
			}
			break;
		}}}
		default:
			break;
		}
	}
}
