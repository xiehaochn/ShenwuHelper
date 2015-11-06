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
            	//��ʾ�������Dialog
            	MyDialog builder1=new MyDialog(getContext(),R.style.myDialog,R.layout.bug_dialog_layout);         
                builder1.setCancelable(true);        
                builder1.show();
            }
        });
        Button pw_button2 = (Button) contentView.findViewById(R.id.pw_button2);
        pw_button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            	//��ʾ���ⷴ��Dialog
            	MyDialog builder2=new MyDialog(getContext(),R.style.myDialog,R.layout.about_dialog_layout);         
                builder2.setCancelable(true);        
                builder2.show();
            }
        });
        Button pw_button3=(Button)contentView.findViewById(R.id.pw_button3);
        pw_button3.setOnClickListener(new OnClickListener(){
        	
			@Override
			public void onClick(View v) {
				//������		
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
                // �����������true�Ļ���touch�¼���������
                // ���غ� PopupWindow��onTouchEvent�������ã���������ⲿ�����޷�dismiss
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
				//������֮�󵯳�����Ի���
				URL_download=msg.getData().getString("URL_download");
				APKname=msg.getData().getString("APKname");
				newVersion=msg.getData().getInt("newVersion");
				if(oldVersion<newVersion){
					AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
					builder.setTitle("���ֿ��ø���");
					builder.setIcon(R.drawable.ic_launcher);
					builder.setMessage("��⵽���ø��£��Ƿ��������ذ�װ��");
					builder.setCancelable(true);
					builder.setPositiveButton("��������", new DialogInterface.OnClickListener() {			
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//ѡ����������
							dialog.dismiss();						
						}
					});
					builder.setNegativeButton("�Ժ����", new DialogInterface.OnClickListener() {						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// ѡ���Ժ����
							dialog.dismiss();
						}
					});
					builder.show();
				}else{
					Toast.makeText(getContext(), "�������°汾����ǰ�汾��Ϊ��"+oldVersion, Toast.LENGTH_SHORT).show();
				}				
				break;
			}
			}
		}
	};
	//����HTTP�����ȡ���°汾��Ϣ
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
				Toast.makeText(getContext(), "���ӳ�ʱ��������", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}finally{
			if(con!=null){
				con.disconnect();
			}	
			}}
		}).start();
	}
	//��ȡ��ǰ�汾��Ϣ
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
