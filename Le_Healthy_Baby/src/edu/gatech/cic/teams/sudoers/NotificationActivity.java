package edu.gatech.cic.teams.sudoers;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NotificationActivity extends ListActivity {
	private int mChildId;
	private String mChildName;
	private ArrayList<MyNotification> m;
	private ArrayAdapter<MyNotification> adapter;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
				"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2" };
		MyNotification[] temp = { new MyNotification("lol"),
				new MyNotification("Another Lol") };
		m = new ArrayList<MyNotification>(Arrays.asList(temp));
		adapter = new NotificationsAdapter(this, m);
		getListView().addHeaderView(
				View.inflate(this, R.layout.notifications_header, null));
		getListView().addFooterView(
				View.inflate(this, R.layout.add_data_button_layout, null));
		mChildId = getIntent().getExtras().getInt("childId");
		mChildName = getIntent().getExtras().getString("childName");
		setTitle(mChildName);
		setListAdapter(adapter);
	}

	public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),
				AddHeightWeightDataActivity.class);
		i.putExtra("childId", mChildId);
		i.putExtra("childName", mChildName);
		startActivity(i);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		if (position > 0) {
			MyNotification item = (MyNotification) getListAdapter().getItem(
					position - 1);
			if (item.isCheck) {
				m.remove(position - 1);
				adapter = new NotificationsAdapter(this, m);
				setListAdapter(adapter);
			} else {
				item.c.setChecked(true);
				item.isCheck = true;
			}
		}
	}
}
