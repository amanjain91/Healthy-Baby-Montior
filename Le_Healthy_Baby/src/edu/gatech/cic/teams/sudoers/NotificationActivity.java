package edu.gatech.cic.teams.sudoers;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class NotificationActivity extends ListActivity {
	private int mChildId;
	private String mChildName;
	private ArrayList<Notification> m;
	private NotificationsAdapter adapter;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		mChildId = getIntent().getExtras().getInt("childId");
		mChildName = getIntent().getExtras().getString("childName");
		SQLiteDatabase db = new DatabaseOpenHelper(this).getReadableDatabase();
		Cursor c = db.query(Child.getNotificationTableName(mChildId),
				new String[] { "id", "ntype", "value", "vcheck" }, null, null,
				null, null, null);
		c.moveToFirst();
		String typeOfNotification, notification = null;
		Notification aNotification = null;
		m = new ArrayList<Notification>();
		do {
			if (c.getInt(3) != 2) {
				typeOfNotification = c.getString(1);
				int x = c.getInt(2);
				if (typeOfNotification.equals("Date")) {
					notification = "You haven't entered data since "
							+ c.getInt(2) + " days";
				} else if (typeOfNotification.equals("Height")) {
					if (c.getInt(2) < 0) {
						notification = "Child's Height growth is slow.";
					} else if (c.getInt(2) > 0) {
						notification = "Child's Height growth is abnormaly fast.";
					}
				} else if (typeOfNotification.equals("Weight")) {
					if (c.getInt(2) < 0) {
						notification = "Child seems undernourished.";
					} else if (c.getInt(2) > 0) {
						notification = "Child seems over weighted.";
					}
				} else if (typeOfNotification.equals("Vaccine")) {

					if (x != 0) {
						Cursor temp = db.query(
								Child.getVaccinationTableName(mChildId),
								new String[] { "VACCINE_NAME" },
								"VACCINE_ID=?",
								new String[] { Integer.toString(x) }, null,
								null, null);
						temp.moveToFirst();
						notification = "You have '" + temp.getString(0)
								+ "' vaccination pending.";
						temp.close();
					}
				} else {
					Log.d("NotificationActivity", "What The Fuck? ");
				}
				if (x != 0) {
					aNotification = new Notification(notification, c.getInt(0));
					aNotification.isCheck = (c.getInt(3) == 1);
					m.add(aNotification);
				}
			}
		} while (c.moveToNext());
		c.close();
		db.close();
		adapter = new NotificationsAdapter(this, m);
		getListView().addHeaderView(
				View.inflate(this, R.layout.notifications_header, null));
		getListView().addFooterView(
				View.inflate(this, R.layout.add_data_button_layout, null));

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
			Notification item = (Notification) getListAdapter().getItem(
					position - 1);
			SQLiteDatabase db = new DatabaseOpenHelper(this)
					.getWritableDatabase();
			ContentValues cv = new ContentValues();
			int a = 0;
			if (item.isCheck) {
				m.remove(position - 1);
				adapter = new NotificationsAdapter(this, m);
				setListAdapter(adapter);
				a = 2;
			} else {
				item.c.setChecked(true);
				item.isCheck = true;
				a = 1;
			}
			cv.put("vcheck", a);
			db.update(Child.getNotificationTableName(mChildId), cv, "id=?",
					new String[] { Integer.toString(item.id) });
		}
	}
}
