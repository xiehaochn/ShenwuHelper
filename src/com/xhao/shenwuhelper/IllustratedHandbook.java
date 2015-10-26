package com.xhao.shenwuhelper;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class IllustratedHandbook extends Activity implements OnClickListener {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.illustratedhandbook_layout);
		creatdb();
		findViews();
		setListener();
	}
	private IllustratedHandbookDB dbHelper;
	private void creatdb() {
		// TODO Auto-generated method stub
		dbHelper=new IllustratedHandbookDB(this,"ILLUSTRATEDHANDBOOK.db",null,1);
		dbHelper.getReadableDatabase();

		
	}
	private EditText ih_search_et;
	private ImageButton ih_search_button;
	private TextView name_tx;
	private TextView attack_tx;
	private TextView defence_tx;
	private TextView physique_tx;
	private TextView magic_tx;
	private TextView speed_tx;
	private TextView skill_tx;
	private void setListener() {
		ih_search_button.setOnClickListener(this);
		
	}

	private void findViews() {
		ih_search_et=(EditText)findViewById(R.id.ih_search_et);
		ih_search_button=(ImageButton)findViewById(R.id.ih_search_button);
		name_tx=(TextView)findViewById(R.id.name);
		attack_tx=(TextView)findViewById(R.id.attack);
		defence_tx=(TextView)findViewById(R.id.defence);
		physique_tx=(TextView)findViewById(R.id.physique);
		magic_tx=(TextView)findViewById(R.id.magic);
		speed_tx=(TextView)findViewById(R.id.speed);
		skill_tx=(TextView)findViewById(R.id.skill);
	}
	private String name;
	private String attack; 
	private String defence;
	private String physique;
	private String magic;
	private String speed;
	private String skill;
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case(R.id.ih_search_button):{
			String ih_search_text;
			ih_search_text=ih_search_et.getText().toString();
			if("".equals(ih_search_text)){
				
				Toast.makeText(this, "请输入怪物名称", Toast.LENGTH_SHORT).show();
			}else
			{
				try{
				SQLiteDatabase db=dbHelper.getWritableDatabase();
				Cursor cursor=db.rawQuery("select * from IH_table where ih_name=?", new String[]{ih_search_text});
				if(cursor.moveToFirst()) {
				    name = cursor.getString(cursor.getColumnIndex("ih_name"));
				    attack = cursor.getString(cursor.getColumnIndex("ih_attack"));
				    defence = cursor.getString(cursor.getColumnIndex("ih_defence"));
				    physique= cursor.getString(cursor.getColumnIndex("ih_physique"));
				    magic= cursor.getString(cursor.getColumnIndex("ih_magic"));
				    speed= cursor.getString(cursor.getColumnIndex("ih_speed"));
				    skill= cursor.getString(cursor.getColumnIndex("ih_skill"));
				    IHrefresh();
				    
				}
				}catch(Exception obj){
					Toast.makeText(this, "未能找到结果", Toast.LENGTH_SHORT).show();
				}
			}
			break;
		}
		default:
			break;
		}
	}

	private void IHrefresh() {
		// TODO Auto-generated method stub
		name_tx.setText(name);
		attack_tx.setText(attack);
		defence_tx.setText(defence);
		physique_tx.setText(physique);
		magic_tx.setText(magic);
		speed_tx.setText(speed);
		skill_tx.setText(skill);
	}
}
