package edu.gatech.cic.teams.sudoers;

import android.widget.CheckBox;

public class MyNotification {
	public CheckBox c;
	public String notification;
	public boolean isCheck;

	public MyNotification(String n) {
		notification = n;
		isCheck = false;
	}
}