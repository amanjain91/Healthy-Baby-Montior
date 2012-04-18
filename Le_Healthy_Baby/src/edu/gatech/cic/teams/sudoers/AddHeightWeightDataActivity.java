package edu.gatech.cic.teams.sudoers;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.EditText;

public class AddHeightWeightDataActivity extends Activity {
	private EditText mWeight, mHeight;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_height_weight_data_layout);
		mWeight = (EditText) findViewById(R.id.weight);
		mHeight = (EditText) findViewById(R.id.height);
		setTitle(getIntent().getExtras().getString("childName"));
	}

	public void onClick(View v) {
		SQLiteDatabase db = new DatabaseOpenHelper(getApplicationContext())
				.getWritableDatabase();
		ContentValues cv = new ContentValues();
		Cursor c = db.query(
				"children",
				new String[] { "day", "month", "year" },
				"child_id=?",
				new String[] { Integer.toString(getIntent().getExtras().getInt(
						"childId")) }, null, null, null);
		c.moveToFirst();
		int w = Integer.parseInt(mWeight.getText().toString());
		int h = Integer.parseInt(mHeight.getText().toString());
		cv.put("weight", w);
		cv.put("height", h);
		cv.put("bmi", (w * 703.0) / (h ^ 2));
		cv.put("day", getDays(c.getInt(0), c.getInt(1), c.getInt(2)));
		db.insertWithOnConflict(Child.getDataTableName(getIntent().getExtras()
				.getInt("childId")), null, cv, SQLiteDatabase.CONFLICT_REPLACE);
		c.close();
		db.close();
		finish();
	}

	public static int getDays(int day, int month, int year) {
		Time bDay, current;
		bDay = new Time();
		current = new Time();
		current.setToNow();
		bDay.set(day, month, year);
		current.normalize(true);
		bDay.normalize(true);
		return Time.getJulianDay(current.toMillis(true), 0)
				- Time.getJulianDay(bDay.toMillis(true), 0);
	}
}
