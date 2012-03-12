package edu.gatech.cic.teams.sudoers;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * This activity provides a form for adding a new child to be tracked in the
 * application.
 * 
 * @author Suren_Nihalani
 * @version 1.0
 */
public class NewChildActivity extends Activity {

	/** The KeyValueMap used at the time of submission. */
	private ContentValues mValues;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_child_layout);
	}

	public void onClick(View v) {
		SQLiteDatabase db = null;
		Log.v(this.getClass().getSimpleName(), "Submit button Clicked!");
		try {
			db = new DatabaseOpenHelper(getApplicationContext())
					.getWritableDatabase();
			mValues = new ContentValues();
			mValues.put(DatabaseOpenHelper.CHILD_NAME,
					((EditText) findViewById(R.id.edit_name)).getText()
							.toString());
			db.insert(DatabaseOpenHelper.CHILDREN_TABLE_NAME, null, mValues);
		} finally {
			db.close();
		}
		finish();
	}

	public String toString() {
		return getClass().getSimpleName();
	}
}
