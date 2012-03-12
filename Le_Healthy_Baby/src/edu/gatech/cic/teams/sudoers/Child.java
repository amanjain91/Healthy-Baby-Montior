package edu.gatech.cic.teams.sudoers;

public class Child {
	private String mName;

	public void setName(String string) {
		mName = string;
	}

	public String getName() {
		return mName;
	}

	public String toString() {
		return "Child: " + mName;
	}
}
