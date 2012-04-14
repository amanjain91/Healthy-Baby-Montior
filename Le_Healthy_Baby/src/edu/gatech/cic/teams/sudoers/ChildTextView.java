/** Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php */
package edu.gatech.cic.teams.sudoers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

/**
 * A TextView that just displays the name of the child and on clicking it, leads
 * to the next activity and passes the child object.
 * 
 * @author Suren_Nihalani
 * @version 1.0
 * 
 */
public class ChildTextView extends TextView implements OnClickListener {
	/**
	 * The child object that shall be pass to the next screen on clicking this
	 * text view
	 */
	private final Child mChild;
	/** The text size of the text displayed in this view */
	private static final int TEXT_SIZE = 25;
	/**
	 * The variable required to implement alternating colors for the textview in
	 * the list
	 */
	private static boolean sColor = false;
	private Context mContext;

	/**
	 * The constructor method.
	 * 
	 * @param context
	 *            The application context. required for super.
	 * @param aChild
	 *            The child represented by this object.
	 */
	public ChildTextView(Context context, Child aChild) {
		super(context);
		mChild = aChild;
		if (sColor) {
			setBackgroundResource(R.color.White);
		} else {
			setBackgroundResource(R.color.Black);
		}
		sColor = !sColor;
		setTextSize(TEXT_SIZE);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		setText(mChild.getName());
		setClickable(true);
		// setOnTouchListener(this);
		setOnClickListener(this);
		mContext = context;
	}

	/**
	 * The String representation of this object.
	 * 
	 * 
	 * @return The class name in String.
	 */
	public String toString() {
		return getClass().getName();
	}

	/**
	 * The method gets called on clicking this object. <br/>
	 * TODO: Make this go to the stream homepage.
	 * 
	 * @param v
	 *            The view the was clicked.
	 */
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.setClass(mContext, ChildScreen.class);
		intent.putExtra("childId", mChild.getChildId());
		intent.putExtra("childName", mChild.getName());
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		v.getContext().startActivity(intent);
	}
}
