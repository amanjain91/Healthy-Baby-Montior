package edu.gatech.cic.teams.sudoers;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class NotificationActivity extends ListActivity {
	private int mChildId;
	private String mChildName;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
				"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, values);
		getListView().addHeaderView(
				View.inflate(this, R.layout.add_data_button_layout, null));
		setListAdapter(adapter);
		mChildId = getIntent().getExtras().getInt("childId");
		mChildName = getIntent().getExtras().getString("childName");
		setTitle(mChildName);
	}

	public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),
				AddHeightWeightDataActivity.class);
		i.putExtra("childId", mChildId);
		i.putExtra("childName", mChildName);
		startActivity(i);
	}
}
