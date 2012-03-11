package edu.gatech.cic.teams.sudoers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
	public static final String CHILD_ID = "child_id";
	public static final String CHILD_NAME = "name";
	private static final String CREATE_CHILDREN_TABLE = "create table children ( "
			+ CHILD_ID + " int NOT NULL AUTOINCREMENT, name varchar(50));";
	private static final int DATABASE_VERSION = 3;
	public static final String CHILDREN_TABLE_NAME = "children";
	// private static final String CHILDREN_TABLE_CREATE = "CREATE TABLE "
	// + CHILDREN_TABLE_NAME + " (" + KEY_WORD + " TEXT, "
	// + KEY_DEFINITION + " TEXT);";
	private static final String DATABASE_NAME = "Le_Healthy_Baby";

	DatabaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_CHILDREN_TABLE);
		db.execSQL("insert into children (name) values ('Le Chut');");
		db.execSQL("insert into children (name) values ('Gurden');");
		db.execSQL("insert into children (name) values ('Chodkhan');");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP children;");
		onCreate(db);
	}
}