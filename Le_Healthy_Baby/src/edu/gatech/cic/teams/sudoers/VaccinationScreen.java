/** Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php */
package edu.gatech.cic.teams.sudoers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Calendar;

public class VaccinationScreen extends Activity {
	private LinearLayout mMainLayout;
	private int mm, yy, childId;
	private static String[][] chart = VaccinationData.getChart();
	private static ArrayList<String> vList;

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
		vList = new ArrayList<String>();
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
		// mm = 1;
		// yy = 2011;
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);

		int childYear = year - yy;
		int childMonth;

		if (childYear < 2) {
			childMonth = month - mm;
		}

		else {
			childMonth = 0;
		}

		if (childYear == 0) {
			if (childMonth == 0) {
				addToVList(0);
			}

			else if (childMonth == 1) {
				addToVList(1);
			}

			else if (childMonth > 1 && childMonth <= 3) {
				addToVList(2);
			}

			else if (childMonth > 3 && childMonth <= 5) {
				addToVList(3);
			}

			else if (childMonth > 5 && childMonth <= 8) {
				addToVList(4);
			}

			else if (childMonth > 8 && childMonth <= 11) {
				addToVList(5);
			}
		}

		else if (childYear == 1) {
			if (childMonth >= 0 && childMonth <= 2) {
				addToVList(6);
			}

			else if (childMonth > 2 && childMonth <= 5) {
				addToVList(7);
			}

			else if (childMonth == 6) {
				addToVList(8);
			}

			else if (childMonth > 6 && childMonth <= 11) {
				addToVList(9);
			}
		}

		else if (childYear > 1 && childYear <= 3) {
			addToVList(10);
		}

		else {
			addToVList(11);
		}

		for (int i = 0; i < vList.size(); i++) {
			mMainLayout.addView(new VaccinationView(getApplicationContext(),
					childId, vList.get(i), false));
		}

	}

	public static void addToVList(int c) {
		for (int i = 0; i < 11; i++) {
			if (chart[i][c].compareTo("0") != 0) {
				vList.add(chart[i][c]);
			}
		}
	}
}
