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
	 * FIXME: aman
	 * 
	 * @param id
	 */
	public static void initializeDummyData(int id) {

	}
}
