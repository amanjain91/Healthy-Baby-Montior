package edu.gatech.cic.teams.sudoers;

/**
 * A information holder object for a Child.
 * 
 * @author Suren_Nihalani
 * @version 1.0
 * 
 */
public class Child {
	/** The name of the child */
	private String mName;

	private static final String AT_RATE_OF = "@";

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
		return getClass() + AT_RATE_OF + getName();
	}
}
