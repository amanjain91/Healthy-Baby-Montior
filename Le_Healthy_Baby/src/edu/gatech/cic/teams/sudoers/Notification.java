package edu.gatech.cic.teams.sudoers;

import android.widget.CheckBox;

public class Notification {
	public CheckBox c;
	public String notification;
	public boolean isCheck;
	public int id;

	public Notification(String n, int i) {
		id = i;
		notification = n;
		isCheck = false;
	}
}