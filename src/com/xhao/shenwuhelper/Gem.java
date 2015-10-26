package com.xhao.shenwuhelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Gem extends Activity implements OnClickListener {
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.gem_layout);
	findView();
	setListener();
	}
	private EditText yijijiage_et_1;
	private EditText mubiaodengji_et_1;
	private TextView suoxufeiyong_1;
	private EditText yijijiage_et_2;
	private EditText mubiaodengji_et_2;
	private TextView suoxufeiyong_2;
	private Button queding_1;
	private Button queding_2;
	private void findView() {
		// TODO Auto-generated method stub
		yijijiage_et_1=(EditText)findViewById(R.id.yijijiage_input);
		mubiaodengji_et_1=(EditText)findViewById(R.id.mubiaodengji_input);
		suoxufeiyong_1=(TextView)findViewById(R.id.dangebaoshi_output);
		yijijiage_et_2=(EditText)findViewById(R.id.yijijiage_input_yitao);
		mubiaodengji_et_2=(EditText)findViewById(R.id.mubiaodengji_input_yitao);
		suoxufeiyong_2=(TextView)findViewById(R.id.yitaobaoshi_output);
		queding_1=(Button)findViewById(R.id.dangebaoshi_button);
		queding_2=(Button)findViewById(R.id.yitaobaoshi_button);
	}
	private void setListener() {
		queding_1.setOnClickListener(this);
		queding_2.setOnClickListener(this);
		
	}
	private int yijijiage_1;
	private int mubiaodengji_1;
	private int feiyong_1;
	private int yijijiage_2;
	private int mubiaodengji_2;
	private int feiyong_2;
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.dangebaoshi_button:{
			if("".equals(yijijiage_et_1.getText().toString())|"".equals(mubiaodengji_et_1.getText().toString())){
				Toast.makeText(this, "请输入数值", Toast.LENGTH_SHORT).show();
			}else{
			yijijiage_1=Integer.parseInt(yijijiage_et_1.getText().toString());
			mubiaodengji_1=Integer.parseInt(mubiaodengji_et_1.getText().toString());
			try{
				if(yijijiage_1>=0 & mubiaodengji_1>=0){
				switch(mubiaodengji_1){
				case 0:{
					feiyong_1=0;
					break;
				}
				case 1:{
					feiyong_1=yijijiage_1;
					break;
				}
				case 2:{
					feiyong_1=yijijiage_1*2;
					break;
				}
				case 3:{
					feiyong_1=yijijiage_1*4;
					break;
				}
				default:{
					feiyong_1=(mubiaodengji_1*mubiaodengji_1-7*mubiaodengji_1+14)*4*yijijiage_1;
					break;
				}
				}}else{
					throw new Exception("非法输入");
				}
			}catch(Exception obj){
				Toast.makeText(this, "输入有误", Toast.LENGTH_SHORT).show();
			}
			suoxufeiyong_1.setText(feiyong_1+"");
			break;
		}}
		case R.id.yitaobaoshi_button:{
			if("".equals(yijijiage_et_2.getText().toString())|"".equals(mubiaodengji_et_2.getText().toString())){
				Toast.makeText(this, "请输入数值", Toast.LENGTH_SHORT).show();
			}else{
			yijijiage_2=Integer.parseInt(yijijiage_et_2.getText().toString());
			mubiaodengji_2=Integer.parseInt(mubiaodengji_et_2.getText().toString());
			try{
				if(yijijiage_2>=0 & mubiaodengji_2>=0){
					switch(mubiaodengji_2){
					case 0:{
						feiyong_2=0;
						break;
					}
					case 1:{
						feiyong_2=yijijiage_2;
						break;
					}
					case 2:{
						feiyong_2=yijijiage_2*3;
						break;
					}
					case 3:{
						feiyong_2=yijijiage_2*7;
						break;
					}
					default:{
						feiyong_2=(mubiaodengji_2*mubiaodengji_2-5*mubiaodengji_2+6)*4*yijijiage_2;
						break;
					}
					}}else{
						throw new Exception("非法输入");
					}
			}catch(Exception obj){
				Toast.makeText(this, "输入有误", Toast.LENGTH_SHORT).show();
			}
			suoxufeiyong_2.setText(feiyong_2+"");
			break;
		}}
		default:
			break;
		}
	}
}
