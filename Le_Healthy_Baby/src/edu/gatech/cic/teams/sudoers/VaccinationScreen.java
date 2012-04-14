/** Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php */
package edu.gatech.cic.teams.sudoers;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import java.util.Calendar;

public class VaccinationScreen extends Activity {
	private LinearLayout mMainLayout;
	private int mm, yy, childId;

	/**
	 * 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Vaccinations Taken");
		Intent i = getIntent();
		mm = i.getExtras().getInt("mm");
		yy = i.getExtras().getInt("yy");
		childId = i.getExtras().getInt("childId");
		Log.v(getClass().getSimpleName(), "mm: " + mm);
		Log.v(getClass().getSimpleName(), "yy: " + yy);
		initLayout();
		makeVaccineList();
		setContentView(mMainLayout);
	}

	private void initLayout() {
		mMainLayout = new LinearLayout(getApplicationContext());
		mMainLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		mMainLayout.setOrientation(LinearLayout.VERTICAL);

	}

	public void makeVaccineList() {
		mMainLayout.removeAllViews();
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);

		int childYear = year - yy;
		int childMonth = month - mm;
		int numberOfMonths = (Math.abs(childYear) * 12) + (childMonth);
		SQLiteDatabase db = new DatabaseOpenHelper(getApplicationContext())
				.getReadableDatabase();
		Log.v("VaccinationScreen", "Child ID I got: " + childId);
		Cursor c = db.query(Child.getVaccinationTableName(childId),
				new String[] { "VACCINE_ID", "VACCINE_NAME", "VACC_GIVEN" },
				"START_DATE<=?",
				new String[] { Integer.toString(numberOfMonths) }, null, null,
				null);
		c.moveToFirst();
		int i = 0;
		VaccinationView temp;
		boolean isChecked;
		while (i < c.getCount()) {
			isChecked = (c.getInt(2) == 1);
			temp = new VaccinationView(this, childId,
					c.getInt(0), c.getString(1), isChecked);
			mMainLayout.addView(temp);
			c.moveToNext();
			i++;
		}
		c.close();
		db.close();
	}
}
