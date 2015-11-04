package com.xhao.shenwuhelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class IllustratedHandbookDB extends SQLiteOpenHelper {
	private final Context mcontext;
	private final static String TABLE_NAME = "IH_table";
	private final static String IH_ID = "ih_id";
	private final static String IH_NAME = "ih_name";
	private final static String IH_ATTACK = "ih_attack";
	private final static String IH_DEFENCE = "ih_defence";
	private final static String IH_PHYSIQUE = "ih_physique";
	private final static String IH_MAGIC = "ih_magic";
	private final static String IH_SPEED = "ih_speed";
	private final static String IH_SKILL = "ih_skill";
	private String sql_create = "CREATE TABLE " + TABLE_NAME + " (" + IH_ID
			+ " INTEGER primary key autoincrement, " + IH_NAME + " text, "+ IH_ATTACK +" text, "
			+ IH_DEFENCE +" text, " + IH_PHYSIQUE +" text, "+ IH_MAGIC +" text, "
			+ IH_SPEED +" text, "+ IH_SKILL +" text);";
	public IllustratedHandbookDB(Context context, String name, CursorFactory factory, int version) {
		super(context, name, null,version);
		this.mcontext=context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL(sql_create);//创建表格test.db
		String fileName = "initial.txt";//建表时所需插入数据保存在这个文件
		try {
			renewDB(db,fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		switch(oldVersion){
		case 1:{
			String fileName_v2="upgrade_v2.txt";
			try {
				renewDB(db,fileName_v2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		default:
			break;
		}
	}
	private void renewDB(SQLiteDatabase db,String fileName) throws IOException{   
		   InputStream in = mcontext.getResources().getAssets().open(fileName);   
		   BufferedReader reader =new BufferedReader(new InputStreamReader(in,"utf-8"));
		   String line;
		   while((line=reader.readLine())!=null){
			   db.execSQL(line);
		   }
		   
		   in.close();	  
	}
}

