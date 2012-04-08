package edu.gatech.cic.teams.sudoers;

import android.app.Activity;
import android.os.Bundle;

public class NotificationsActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		NotificationCenter nc = new NotificationCenter(new Child(1,
				getApplicationContext()), getApplicationContext());
		setContentView(nc);
	}
}
