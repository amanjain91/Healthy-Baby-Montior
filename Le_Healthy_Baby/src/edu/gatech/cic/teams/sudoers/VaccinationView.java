/** Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php */
package edu.gatech.cic.teams.sudoers;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class VaccinationView extends LinearLayout {
	// private int childID;
	private String vaccineName;
	private CheckBox vaccineCheckBox;
	private TextView textView;

	public VaccinationView(Context context, int childID, String vaccineName,
			boolean checkStatus) {
		super(context);
		// this.childID = childID;
		this.vaccineName = vaccineName;

		vaccineCheckBox = new CheckBox(context);
		this.vaccineCheckBox.setChecked(checkStatus);
		setOrientation(HORIZONTAL);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));

		textView = new TextView(context);
		textView.setText(this.vaccineName);

		addView(vaccineCheckBox);
		addView(textView);

	}
}
