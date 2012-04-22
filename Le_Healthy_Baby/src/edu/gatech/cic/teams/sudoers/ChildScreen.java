package edu.gatech.cic.teams.sudoers;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class ChildScreen extends TabActivity {
	private Child mChild;
	private String mChildName;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.child_screen_tablayout);
		Intent i = getIntent();
		mChild = new Child(i.getIntExtra("childId", -1),
				getApplicationContext());
		mChildName = i.getStringExtra("childName");
		setTitle(i.getExtras().getCharSequence("childName"));
		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;

		Intent notificationsIntent = new Intent().setClass(this,
				NotificationActivity.class);
		notificationsIntent.putExtra("childId", mChild.getChildId());
		notificationsIntent.putExtra("childName", mChildName);
		spec = tabHost.newTabSpec("notifications").setIndicator("N.")
				.setContent(notificationsIntent);
		tabHost.addTab(spec);

		Intent heightIntent = new HeightChart().execute(
				getApplicationContext(), mChild);
		spec = tabHost.newTabSpec("height").setIndicator("Hgt")
				.setContent(heightIntent);
		tabHost.addTab(spec);

		Intent weightIntent = new WeightChart().execute(
				getApplicationContext(), mChild);
		spec = tabHost.newTabSpec("weight").setIndicator("Wgt")
				.setContent(weightIntent);
		tabHost.addTab(spec);

		intent = new BMIChart().execute(getApplicationContext(), mChild);
		spec = tabHost.newTabSpec("bmi").setIndicator("BMI").setContent(intent);
		tabHost.addTab(spec);

		Intent vaccinationsIntent = new Intent(this, VaccinationScreen.class);
		vaccinationsIntent.putExtra("mm", mChild.getBirthMonth());
		vaccinationsIntent.putExtra("yy", mChild.getBirthYear());
		vaccinationsIntent.putExtra("childId", mChild.getChildId());
		spec = tabHost.newTabSpec("vaccinations").setIndicator("V")
				.setContent(vaccinationsIntent);
		tabHost.addTab(spec);
	}
}
