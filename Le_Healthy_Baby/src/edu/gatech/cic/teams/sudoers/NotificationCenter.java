package edu.gatech.cic.teams.sudoers;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NotificationCenter extends LinearLayout {
	private Child mChild;
	private Context mContext;

	public NotificationCenter(Child id, Context applicationContext) {
		super(applicationContext);
		mChild = id;
		mContext = applicationContext;
		setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		setOrientation(VERTICAL);
		TextView temp = new TextView(mContext);
		temp.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		temp.setText("hello world!");

		Log.v("Notification Center", mChild.toString());
		addView(temp);
	}
}
