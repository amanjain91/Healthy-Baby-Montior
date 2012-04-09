package edu.gatech.cic.teams.sudoers;

import edu.gatech.cic.teams.sudoers.R;
import edu.gatech.cic.teams.sudoers.SongsActivity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class ChildScreenT extends TabActivity {
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
		Resources res = getResources();
		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;

		Intent notificationsIntent = new Intent().setClass(this,
				NotificationActivity.class);
		notificationsIntent.putExtra("childId", mChild.getChildId());
		notificationsIntent.putExtra("childName", mChildName);
		spec = tabHost
				.newTabSpec("notifications")
				.setIndicator("Home",
						res.getDrawable(R.drawable.ic_tab_artists_grey))
				.setContent(notificationsIntent);
		tabHost.addTab(spec);

		Intent heightIntent = new MyChart().execute(getApplicationContext(),
				mChild);
		spec = tabHost
				.newTabSpec("height")
				.setIndicator("Hgt",
						res.getDrawable(R.drawable.ic_tab_albums_grey))
				.setContent(heightIntent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, SongsActivity.class);
		spec = tabHost
				.newTabSpec("weight")
				.setIndicator("Wgt",
						res.getDrawable(R.drawable.ic_tab_albums_grey))
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, SongsActivity.class);
		spec = tabHost
				.newTabSpec("bmi")
				.setIndicator("BMI",
						res.getDrawable(R.drawable.ic_tab_albums_grey))
				.setContent(intent);
		tabHost.addTab(spec);

		Intent vaccinationsIntent = new Intent(this, VaccinationScreen.class);
		vaccinationsIntent.putExtra("mm", mChild.getBirthMonth());
		vaccinationsIntent.putExtra("yy", mChild.getBirthYear());
		vaccinationsIntent.putExtra("childId", mChild.getChildId());
		spec = tabHost
				.newTabSpec("vaccinations")
				.setIndicator("V",
						res.getDrawable(R.drawable.ic_tab_albums_grey))
				.setContent(vaccinationsIntent);
		tabHost.addTab(spec);
	}
}
