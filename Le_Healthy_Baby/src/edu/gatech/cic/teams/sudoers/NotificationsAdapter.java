package edu.gatech.cic.teams.sudoers;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class NotificationsAdapter extends ArrayAdapter<Notification> {
	private final Activity context;
	private final List<Notification> notifications;

	public NotificationsAdapter(Activity context, List<Notification> names) {
		super(context, R.layout.rowlayout, names);
		this.context = context;
		this.notifications = names;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.rCheckBox);
		notifications.get(position).c = checkBox;
		if (notifications.get(position).isCheck) {
			checkBox.setChecked(true);
		}
		checkBox.setClickable(false);
		textView.setText(notifications.get(position).notification);
		// Change the icon for Windows and iPhone
		return rowView;
	}
}
