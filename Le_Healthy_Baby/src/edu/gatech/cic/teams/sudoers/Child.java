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

	/**
	 * Initializes the object with the given name.
	 * 
	 * @param name
	 *            The name of this child.
	 */
	public Child(String name) {
		mName = name;
	}

	/**
	 * Default constructor for initializing the child with no name.
	 */
	public Child() {
		this("");
	}

	public String getName() {
		return mName;
	}

	public String toString() {
		return getClass() + "@" + getName();
	}
}
