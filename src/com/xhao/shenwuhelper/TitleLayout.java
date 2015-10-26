package com.xhao.shenwuhelper;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

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
            	MyDialog builder1=new MyDialog(getContext(),R.style.myDialog,R.layout.bug_dialog_layout);         
                builder1.setCancelable(true);        
                builder1.show();
            }
        });
        Button pw_button2 = (Button) contentView.findViewById(R.id.pw_button2);
        pw_button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            	MyDialog builder2=new MyDialog(getContext(),R.style.myDialog,R.layout.about_dialog_layout);         
                builder2.setCancelable(true);        
                builder2.show();
            }
        });
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

}
