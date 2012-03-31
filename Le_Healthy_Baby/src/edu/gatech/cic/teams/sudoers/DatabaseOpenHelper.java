/** Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php */

package edu.gatech.cic.teams.sudoers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/***
 * 
 * DatabaseOpenHelper class initializes the tables, copies data on database
 * upgrades and provides Readable and Writable Databases.
 * 
 * @author Suren_Nihalani
 * @version 1.0
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
	/**
	 * The current version of the database required.<br/>
	 * Lower value might lead to call on upgrade.
	 */
	public static final int DATABASE_VERSION = 3;

	/**
	 * The table name containing list of all children managed by the
	 * application.
	 */
	public static final String CHILDREN_TABLE_NAME = " children ";

	/**
	 * The column name in CHILDREN_TABLE_NAME for uniquely identifying the
	 * child.
	 */
	public static final String CHILD_ID = " child_id ";

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
			+ CHILDREN_TABLE_NAME + " ( " + CHILD_ID + " INTEGER PRIMARY KEY, "
			+ CHILD_NAME + " TEXT " + " );";

	/**
	 * Constructor method. Gets the database object for the current context.
	 * 
	 * @param context
	 */
	protected DatabaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * The method called on creation of this object. Creates the tables and puts
	 * dummy data.
	 * 
	 * @param db
	 *            The database object passed initially to work with.
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		ContentValues values = new ContentValues();
		db.execSQL("DROP TABLE IF EXISTS children;");
		db.execSQL(CREATE_CHILDREN_TABLE);

		values.put(CHILD_NAME, "Suren Nihalani");
		db.insert(CHILDREN_TABLE_NAME, CHILD_NAME, values);
		values = new ContentValues();
		values.put(CHILD_NAME, "Aman");
		db.insert(CHILDREN_TABLE_NAME, CHILD_NAME, values);
		values = new ContentValues();
		values.put(CHILD_NAME, "Gurden");
		db.insert(CHILDREN_TABLE_NAME, CHILD_NAME, values);
		HealthData data = new HealthData();
		data.initLengthData();
		for (int i = 0; i < data.lengthdata.length; i++) {
			db.execSQL(data.lengthdata[i]);
		}
		db.execSQL("DROP TABLE IF EXISTS weightchart;");
		db.execSQL("CREATE TABLE weightchart (Day INTEGER PRIMARY KEY, L Integer, M Double, S Double, P01 Double, P1 Double, P3 Double, P5 Double, P10 Double, P15 Double, P25 Double, P50 Double, P75 Double, P85 Double, P90 Double, P95 Double, P97 Double, P99 Double, P999 Double);");
		for (int i=0; i<data.weightdata.length; i++) {
			values = new ContentValues();
			values.put("Day", i);
			values.put("L", data.weightdata[i][1]);
			values.put("M", data.weightdata[i][2]);
			values.put("S", data.weightdata[i][3]);
			values.put("P01", data.weightdata[i][4]);
			values.put("P1", data.weightdata[i][5]);
			values.put("P3", data.weightdata[i][6]);
			values.put("P5", data.weightdata[i][7]);
			values.put("P10", data.weightdata[i][8]);
			values.put("P15", data.weightdata[i][9]);
			values.put("P25", data.weightdata[i][10]);
			values.put("P50", data.weightdata[i][11]);
			values.put("P75", data.weightdata[i][12]);
			values.put("P85", data.weightdata[i][13]);
			values.put("P90", data.weightdata[i][14]);
			values.put("P95", data.weightdata[i][15]);
			values.put("P97", data.weightdata[i][16]);
			values.put("P99", data.weightdata[i][17]);
			values.put("P999", data.weightdata[i][18]);
			db.insert("weightchart", null, values);
		}

	}

	/**
	 * Moves data from original database to the newer version.<br/>
	 * TODO: Add queries to copy data from original to new one.
	 * 
	 * @param db
	 *            The new database where data is to be copied.
	 * @param oldVersion
	 *            The current version of the database
	 * @param newVersion
	 *            The version of the db passed into this method.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.v("DatabaseOpenHelper", "Database Upgrade called!");
		db.execSQL("DROP TABLE IF EXISTS children;");
		onCreate(db);
	}

	/**
	 * Just returns the class name. Implemented to adhere to the CodePro
	 * standards.
	 * 
	 * 
	 * @return The simple class name of this object.
	 */
	public String toString() {
		return getClass().getSimpleName();
	}

	public void insertLengthData(String SQL) {

	}
}
