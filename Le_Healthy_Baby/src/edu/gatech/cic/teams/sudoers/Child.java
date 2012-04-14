/** Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php */
package edu.gatech.cic.teams.sudoers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * A information holder object for a Child.<br/>
 * TODO: Add support for more fields from the AddChildActivity.
 * 
 * @author Suren_Nihalani
 * @version 1.0
 * 
 */
public class Child {
	/** The name of the child */
	private String mName;
	private int childId;
	private SQLiteDatabase myDb;
	private int mBirthMonth;
	private int mBirthYear;

	/**
	 * Initializes the object with the given name.
	 * 
	 * @param id
	 *            The index of this child in the all children table.
	 */
	public Child(int id, Context c) {
		childId = id;
		myDb = new DatabaseOpenHelper(c).getReadableDatabase();
		Cursor mC = myDb.query(DatabaseOpenHelper.CHILDREN_TABLE_NAME,
				new String[] { DatabaseOpenHelper.CHILD_NAME }, " "
						+ DatabaseOpenHelper.CHILD_ID + " =?",
				new String[] { Integer.toString(childId) }, null, null, null);
		mC.moveToFirst();
		mName = mC.getString(0);
		// FIXME Suren.
		mBirthMonth = 3;
		mBirthYear = 2012;
		mC.close();
		myDb.close();
	}

	/**
	 * Getter method for the name of the child.
	 * 
	 * 
	 * @return The name of this child.
	 */
	public String getName() {
		return mName;
	}

	public int getChildId() {
		return childId;
	}

	/**
	 * The String representation of this object.
	 * 
	 * 
	 * @return The String representation of this object <br/>
	 *         ie. (Child@It's name).
	 */
	public String toString() {
		return getClass() + "@" + getName();
	}

	public String getDataTableName() {
		return "data_" + Integer.toString(childId);
	}

	/**
	 * 
	 * 
	 * @param id
	 */
	public static String[] initializeDummyData(int id) {
		String[] notificationStatements = initializeNotificationCenter(id);
		String mTableName = "data_" + id;
		String[] answer = new String[12 + notificationStatements.length];
		int x = 0;
		answer[x++] = "DROP TABLE IF EXISTS " + mTableName + ";";
		answer[x++] = "CREATE TABLE "
				+ mTableName
				+ " (Day INTEGER PRIMARY KEY, Height Double, Weight Double, BMI Double);";

		for (int i = 0; i < 10; i++) {
			answer[x++] = "INSERT INTO " + mTableName + " VALUES (" + (i * 3)
					+ " , " + (45 + Math.pow(-1, i) * i * 3) + " , 1, 1"
					+ "); ";
		}
		for (String s : notificationStatements) {
			answer[x++] = s;
		}
		return answer;
	}

	public int getBirthMonth() {
		return mBirthMonth;
	}

	public int getBirthYear() {
		return mBirthYear;
	}

	public static String getVaccinationTableName(int AchildId) {
		return "vaccinations_of_" + AchildId;
	}

	public static String getNotificationTableName(int mChildId) {
		return " notifications_of_" + mChildId + " ";
	}

	private static String[] initializeNotificationCenter(int mChildId) {
		String[] reasons = { "Date", "Height", "Weight", "Vaccine" };
		String[] statements = new String[reasons.length + 1];
		String tableName = Child.getNotificationTableName(mChildId);
		statements[0] = "CREATE TABLE IF NOT EXISTS"
				+ tableName
				+ "  ( id INTEGER PRIMARY KEY, ntype Text, value INTEGER , vcheck INTEGER ); ";
		for (int i = 0; i < reasons.length; i++) {
			statements[i + 1] = "INSERT INTO " + tableName + " VALUES ( "
					+ (i + 1) + " , '" + reasons[i] + "' , 0, 0 ) ;";
		}
		return statements;
	}
}
