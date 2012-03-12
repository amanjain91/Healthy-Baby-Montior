package edu.gatech.cic.teams.sudoers;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class ChildTextView extends TextView implements OnTouchListener,
		OnClickListener {
	private final Child mChild;
	private static final int TEXT_SIZE = 25;
	private static boolean mColor = false;

	public ChildTextView(Context context, Child aChild) {
		super(context);
		mChild = aChild;
		if (mColor) {
			setBackgroundResource(R.color.White);
		} else {
			setBackgroundResource(R.color.Black);
		}
		mColor = !mColor;
		setTextSize(TEXT_SIZE);
		setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		setText(mChild.getName());
		setClickable(true);
		// setOnTouchListener(this);
		setOnClickListener(this);
	}

	public String toString() {
		return getClass().getName();
	}

	public void onClick(View v) {
		Log.v(toString(), "Internal OnCCalled! for Child: " + mChild.getName());
	}

	public boolean onTouch(View v, MotionEvent event) {
		Log.v(toString(), "Internal OnTouchCalled!");
		return true;
	}
}
