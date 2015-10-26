package com.xhao.shenwuhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class IllustratedHandbookDB extends SQLiteOpenHelper {
	private final static String DATABASE_NAME = "ILLUSTRATEDHANDBOOK.db";
	private final static int DATABASE_VERSION = 1;
	private final static String TABLE_NAME = "IH_table";
	private final static String IH_ID = "ih_id";
	private final static String IH_NAME = "ih_name";
	private final static String IH_ATTACK = "ih_attack";
	private final static String IH_DEFENCE = "ih_defence";
	private final static String IH_PHYSIQUE = "ih_physique";
	private final static String IH_MAGIC = "ih_magic";
	private final static String IH_SPEED = "ih_speed";
	private final static String IH_SKILL = "ih_skill";
	private String sql = "CREATE TABLE " + TABLE_NAME + " (" + IH_ID
			+ " INTEGER primary key autoincrement, " + IH_NAME + " text, "+ IH_ATTACK +" text, "
			+ IH_DEFENCE +" text, " + IH_PHYSIQUE +" text, "+ IH_MAGIC +" text, "
			+ IH_SPEED +" text, "+ IH_SKILL +" text);";
	public IllustratedHandbookDB(Context context, String name, CursorFactory factory, int version) {
		super(context, DATABASE_NAME, null,DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
				db.execSQL(sql);
				String value1 = "insert into IH_table(ih_id,ih_name,ih_attack,ih_defence,ih_physique,ih_magic"
						+ ",ih_speed,ih_skill) values ('1000001','大海龟','900-1125','800-1000','900-1125','700-875','1100-1375','防御、水系吸收、幸运、慧根')";
				String value2="insert into IH_table(ih_id,ih_name,ih_attack,ih_defence,ih_physique,ih_magic"
						+ ",ih_speed,ih_skill) values  ('1000002','大耳兔','950-1206','800-1016','900-1143','700-889','1100-1397','敏捷、反击、灵性')";
				db.execSQL(value1);
				db.execSQL(value2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		/*String value2="insert into IH_table(ih_id,ih_name,ih_attack,ih_defence,ih_physique,ih_magic"
				+ ",ih_speed,ih_skill) values  ('1000002','大耳兔','950-1206','800-1016','900-1143','700-889','1100-1397','敏捷、反击、灵性')";
		switch (oldVersion){
		case 1:{
			db.execSQL(value2);
			break;
		}
		default:
			break;
		}
		db.execSQL(value2);*/
	}

}
