/** Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php */
package edu.gatech.cic.teams.sudoers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

;
public class VaccinationView extends LinearLayout implements OnClickListener {
	// private int childID;
	private String vaccineName;
	private CheckBox vaccineCheckBox;
	private TextView textView;
	private int mVacId;
	private Context mContext;
	private int mChildId;

	public VaccinationView(Context context, int childId, int vacId,
			String vaccineName, boolean checkStatus) {
		super(context);
		// this.childID = childID;
		this.vaccineName = vaccineName;
		mChildId = childId;
		mVacId = vacId;
		mContext = context;
		vaccineCheckBox = new CheckBox(context);
		this.vaccineCheckBox.setChecked(checkStatus);
		setOrientation(HORIZONTAL);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));

		textView = new TextView(context);
		textView.setText(this.vaccineName);

		addView(vaccineCheckBox);
		addView(textView);
		vaccineCheckBox.setOnClickListener(this);
	}

	public void onClick(View v) {
		SQLiteDatabase db = new DatabaseOpenHelper(mContext)
				.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("VACC_GIVEN", (vaccineCheckBox.isChecked()) ? 1 : 0);
		db.update(Child.getVaccinationTableName(mChildId), cv, "VACCINE_ID=?",
				new String[] { Integer.toString(mVacId) });
		db.close();
	}
}
