package edu.gatech.cic.teams.sudoers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
	/**
	 * The current version of the database required.<br/>
	 * Lower value might lead to call on upgrade.
	 */
	private static final int DATABASE_VERSION = 3;

	/**
	 * The table name containing list of all children managed by the
	 * application.
	 */
	public static final String CHILDREN_TABLE_NAME = " children ";

	/**
	 * The column name in CHILDREN_TABLE_NAME for uniquely identifying the
	 * child.
	 */
	public static final String CHILD_ID = "child_id";

	/** The column name in CHILDREN_TABLE_NAME for the name of the child. */
	public static final String CHILD_NAME = "name";

	/** The name of the database for this application. */
	private static final String DATABASE_NAME = "Le_Healthy_Baby";

	/**
	 * List of the columns currently in the database representated as a list of
	 * strings.
	 */
	public static final String[] CHILDREN_ALL_COLUMNS = { CHILD_ID, CHILD_NAME };

	/** the query to create the table initially. */
	private static final String CREATE_CHILDREN_TABLE = "CREATE TABLE "
			+ CHILDREN_TABLE_NAME + " ( " + CHILD_ID + "INTEGER PRIMARY KEY, "
			+ CHILD_NAME + " TEXT " + " );";

	DatabaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS children;");
		db.execSQL(CREATE_CHILDREN_TABLE);
		ContentValues values = new ContentValues();
		values.put(CHILD_NAME, "Chyut");
		db.insert(CHILDREN_TABLE_NAME, CHILD_NAME, values);
		values = new ContentValues();
		values.put(CHILD_NAME, "Harami");
		db.insert(CHILDREN_TABLE_NAME, CHILD_NAME, values);
		values = new ContentValues();
		values.put(CHILD_NAME, "Madarjaat");
		db.insert(CHILDREN_TABLE_NAME, CHILD_NAME, values);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.v("DatabaseOpenHelper", "Database Upgrade called!");
		db.execSQL("DROP TABLE IF EXISTS children;");
		onCreate(db);
	}

	public String toString() {
		return getClass().getSimpleName();
	}
}
