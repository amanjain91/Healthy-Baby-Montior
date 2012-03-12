package edu.gatech.cic.teams.sudoers;

public class Child {
	/** The name of the child */
	private String mName;

	public Child(String name) {
		mName = name;
	}

	public Child() {
		this("");
	}

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
