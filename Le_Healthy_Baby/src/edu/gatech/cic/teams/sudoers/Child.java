/** Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php */
package edu.gatech.cic.teams.sudoers;

/**
 * A information holder object for a Child.<br/>
 * TODO: Add support for more fields from the AddChildActivity.
 * 
 * @author Suren_Nihalani
 * @version 1.0
 * 
 */
public class Child {
	/** The name of the child */
	private String mName;
	private int childId;

	/**
	 * Initializes the object with the given name.
	 * 
	 * @param name
	 *            The name of this child.
	 */
	public Child(int id) {
		childId = id;
	}

	/**
	 * Getter method for the name of the child.
	 * 
	 * 
	 * @return The name of this child.
	 */
	public String getName() {
		return mName;
	}

	public int getChildId() {
		return childId;
	}

	/**
	 * The String representation of this object.
	 * 
	 * 
	 * @return The String representation of this object <br/>
	 *         ie. (Child@It's name).
	 */
	public String toString() {
		return getClass() + "@" + getName();
	}
}
