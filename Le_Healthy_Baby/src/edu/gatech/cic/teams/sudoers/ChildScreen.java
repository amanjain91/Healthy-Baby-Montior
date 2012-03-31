package edu.gatech.cic.teams.sudoers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChildScreen extends Activity implements OnClickListener {
	/** The child represented by this screen. */
	private Child mChild;

	/** The notification center that will update itself */
	private NotificationCenter mNC;

	/** The button for showing the Height Graph */
	private Button mHeightGraph;

	/** The button for showing the weight graph */
	private Button mWeightGraph;
	
	/** The button for show the weight graph */
	private Button mBmiGraph;
	
	private Button mVaccineGraph;
	private Button mEditProfile;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/** The top layout */
		LinearLayout topLayout = new LinearLayout(getApplicationContext());
		Intent i = getIntent();
		mChild = new Child(i.getIntExtra("childId", -1),
				getApplicationContext());
		mHeightGraph = new Button(getApplicationContext());
		mWeightGraph = new Button(getApplicationContext());
		mBmiGraph = new Button(getApplicationContext());
		mVaccineGraph = new Button(getApplicationContext());
		mEditProfile = new Button(getApplicationContext());
		TextView t = new TextView(this.getApplicationContext());
		mNC = new NotificationCenter(new Child(1, getApplicationContext()),
				getApplicationContext());
		LinearLayout buttonsLayout = new LinearLayout(getApplicationContext());
		Log.v(getClass().getSimpleName(), "ChildScreen OnCreate called!");

		mHeightGraph.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		mWeightGraph.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		mBmiGraph.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));

		mVaccineGraph.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		mEditProfile.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		topLayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));

		mHeightGraph.setText("H");
		mWeightGraph.setText("W");
		mBmiGraph.setText("B");
		mVaccineGraph.setText("V");
		mEditProfile.setText("E");

		mHeightGraph.setOnClickListener(this);
		mWeightGraph.setOnClickListener(this);
		mBmiGraph.setOnClickListener(this);
		mVaccineGraph.setOnClickListener(this);
		mEditProfile.setOnClickListener(this);

		topLayout.setOrientation(LinearLayout.VERTICAL);
		buttonsLayout.setOrientation(LinearLayout.HORIZONTAL);

		t.setText("Testing a TextView");
		topLayout.addView(t);

		buttonsLayout.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		buttonsLayout.addView(mHeightGraph);
		buttonsLayout.addView(mWeightGraph);
		buttonsLayout.addView(mBmiGraph);
		buttonsLayout.addView(mVaccineGraph);
		buttonsLayout.addView(mEditProfile);

		topLayout.addView(mNC);
		topLayout.addView(buttonsLayout);

		setContentView(topLayout);

		Log.v(getClass().getSimpleName(), "End OnCreate()");
	}

	public void onClick(View v) {
		if (v == mHeightGraph) {
			startActivity(new HeightDayChart().execute(getApplicationContext(),
					mChild));
		} else if (v == mWeightGraph) {
		} else if (v == mBmiGraph) {
		} else if (v == mVaccineGraph) {
			Intent i = new Intent(ChildScreen.this, VaccinationScreen.class);
			i.putExtra("mm", mChild.getBirthMonth());
			i.putExtra("yy", mChild.getBirthYear());
			i.putExtra("childId", mChild.getChildId());
			startActivity(i);
		} else if (v == mEditProfile) {
		}
	}
}