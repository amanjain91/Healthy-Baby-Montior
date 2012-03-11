package edu.gatech.cic.teams.sudoers;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class NewChildActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_child_layout);
	}

	public void onClick(View v) {
		Log.v(this.getClass().getSimpleName(), "Submit button Clicked!");
		EditText et = (EditText) findViewById(R.id.edit_name);
		SQLiteDatabase db = new DatabaseOpenHelper(getApplicationContext())
				.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseOpenHelper.CHILD_NAME, et.getText().toString());
		db.insert(DatabaseOpenHelper.CHILDREN_TABLE_NAME, null, values);
		finish();
	}
}
