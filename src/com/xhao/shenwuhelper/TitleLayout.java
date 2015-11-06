package com.xhao.shenwuhelper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class TitleLayout extends LinearLayout {
	public TitleLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.title, this);
		Button button1=(Button)findViewById(R.id.title_back);
		button1.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Activity activity=(Activity)(getContext());
				activity.finish();
			}
			
		});
		Button button2=(Button)findViewById(R.id.title_edit);
		button2.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				showPopupWindow(v);
			}
		});
	}
	protected void showPopupWindow(View v) {
		View contentView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_layout, null);
		Button pw_button1 = (Button) contentView.findViewById(R.id.pw_button1);
        pw_button1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            	//显示关于软件Dialog
            	MyDialog builder1=new MyDialog(getContext(),R.style.myDialog,R.layout.bug_dialog_layout);         
                builder1.setCancelable(true);        
                builder1.show();
            }
        });
        Button pw_button2 = (Button) contentView.findViewById(R.id.pw_button2);
        pw_button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            	//显示问题反馈Dialog
            	MyDialog builder2=new MyDialog(getContext(),R.style.myDialog,R.layout.about_dialog_layout);         
                builder2.setCancelable(true);        
                builder2.show();
            }
        });
        Button pw_button3=(Button)contentView.findViewById(R.id.pw_button3);
        pw_button3.setOnClickListener(new OnClickListener(){
        	
			@Override
			public void onClick(View v) {
				//检测更新		
				getOldVersion();
				sendHttpRequest();				
				};    							
			}       	
        );
		final PopupWindow popupWindow = new PopupWindow(contentView,LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
	
		popupWindow.setTouchable(true);
		popupWindow.setTouchInterceptor(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
		popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.pw_backgroud_shape));
		popupWindow.showAsDropDown(v);
	}
	private String url_getversion="http://10.0.2.2/new_version.json";
	private final int CheckFinished=1;
	private String URL_download;
	private String APKname;
	private int newVersion;
	private int oldVersion;
	private Handler handler =new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
			case CheckFinished:{
				//检测完成之后弹出结果对话框
				URL_download=msg.getData().getString("URL_download");
				APKname=msg.getData().getString("APKname");
				newVersion=msg.getData().getInt("newVersion");
				if(oldVersion<newVersion){
					AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
					builder.setTitle("发现可用更新");
					builder.setIcon(R.drawable.ic_launcher);
					builder.setMessage("检测到可用更新，是否现在下载安装？");
					builder.setCancelable(true);
					builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {			
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//选择立即更新
							dialog.dismiss();						
						}
					});
					builder.setNegativeButton("稍后更新", new DialogInterface.OnClickListener() {						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// 选择稍后更新
							dialog.dismiss();
						}
					});
					builder.show();
				}else{
					Toast.makeText(getContext(), "已是最新版本，当前版本号为："+oldVersion, Toast.LENGTH_SHORT).show();
				}				
				break;
			}
			}
		}
	};
	//发送HTTP请求获取最新版本信息
	public void sendHttpRequest() {
		new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
			HttpURLConnection con=null;
			try{
				URL url_json=new URL(url_getversion);
				con=(HttpURLConnection) url_json.openConnection();
				con.setRequestMethod("GET");
				con.setConnectTimeout(8000);
				con.setReadTimeout(8000);
				InputStream in =con.getInputStream();
				BufferedReader reader =new BufferedReader(new InputStreamReader(in));
				StringBuilder builder=new StringBuilder();
				String line;
				while((line=reader.readLine())!=null){
					builder.append(line);
				}
				String response=builder.toString();
				JSONObject jsonObject=new JSONObject(response);
				int newVersion_ot=jsonObject.getInt("newversion");
				String APKname_ot=jsonObject.getString("name");
				String url_download_ot=jsonObject.getString("url");
				Message message=new Message();
				message.what=CheckFinished;
				Bundle bundle=new Bundle();
				bundle.putInt("newVersion",newVersion_ot);
				bundle.putString("APKname", APKname_ot);
				bundle.putString("URL_download", url_download_ot);
				message.setData(bundle);
				handler.sendMessage(message);
			}catch(Exception e){
				Toast.makeText(getContext(), "连接超时，请重试", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}finally{
			if(con!=null){
				con.disconnect();
			}	
			}}
		}).start();
	}
	//获取当前版本信息
	public int getOldVersion(){
		try {
			 PackageManager manager = getContext().getPackageManager();
			 PackageInfo info = manager.getPackageInfo(getContext().getPackageName(), 0);
			 oldVersion = info.versionCode;
			} catch (Exception e) {
			e.printStackTrace();
			}
		return oldVersion;
	}
}
