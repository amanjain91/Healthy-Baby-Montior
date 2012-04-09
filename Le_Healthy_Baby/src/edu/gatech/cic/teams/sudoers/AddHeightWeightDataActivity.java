package edu.gatech.cic.teams.sudoers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class AddHeightWeightDataActivity extends Activity {
	// private int mChildId;
	// private EditText mWeight, mHeight;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// mWeight = (EditText)
		setTitle(getIntent().getExtras().getString("childName"));
		// mChildId = getIntent().getExtras().getInt("childId");
		setContentView(R.layout.add_height_weight_data_layout);
	}

	public void onClick(View v) {
		finish();
	}
}
