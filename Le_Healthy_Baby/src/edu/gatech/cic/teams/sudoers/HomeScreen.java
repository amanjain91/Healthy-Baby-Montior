package edu.gatech.cic.teams.sudoers;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * This screen lists children as TextViews and on clicking then leads to their
 * notification streams.
 * 
 * @author Suren_Nihalani
 * @version 1.0
 */
public class HomeScreen extends Activity implements OnClickListener {

	/** The button variable for adding a new child */
	private Button mAddChildButton;

	/** The root layout of this activity. */
	private LinearLayout mMainLayout;

	/** The children to be drawn as a list on the screen */
	private Child[] mAllChildren;

	/** The database open helper used by everyone in this activity. */
	private DatabaseOpenHelper mReadableWritableDatabase;

	/** The layout where each ChildTextView should be added. */
	private LinearLayout mChildrenListLayout;

	/** The cursor object to be used for every query result. */
	private Cursor mCursor;

	/**
	 * Made this LayoutParams object static so as to avoid double initialization
	 */
	private static final LayoutParams BOTH_FILL_PARENT = new LayoutParams(
			LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mReadableWritableDatabase = new DatabaseOpenHelper(
				getApplicationContext());
		mReadableWritableDatabase.onUpgrade(
				mReadableWritableDatabase.getWritableDatabase(), 2, 3);
		mReadableWritableDatabase.onCreate(mReadableWritableDatabase
				.getWritableDatabase());
		initLayout();
		getChildren();
		updateChildren();
		setContentView(mMainLayout);
	}

	public void onResume() {
		super.onResume();
		Log.v(getClass().getSimpleName(), "onResume() called.");
		getChildren();
		updateChildren();
		mMainLayout.invalidate();
	}

	private void getChildren() {
		if (null == mReadableWritableDatabase) {
			Log.v("HomeScreen", "DatabaseOpenHelper instance variable is null!");
		}
		SQLiteDatabase db = null;
		try {
			db = mReadableWritableDatabase.getReadableDatabase();
			mCursor = db.query(DatabaseOpenHelper.CHILDREN_TABLE_NAME, null,
					null, null, null, null, null);
			mCursor.moveToFirst();
			Child tempChild;
			mAllChildren = new Child[mCursor.getCount()];
			for (int i = 0; i < mAllChildren.length; i++) {
				tempChild = new Child();
				tempChild.setName(mCursor.getString(mCursor
						.getColumnIndex(DatabaseOpenHelper.CHILD_NAME)));
				mAllChildren[i] = tempChild;
				mCursor.moveToNext();
			}
		} finally {
			db.close();
		}
	}

	private void initLayout() {
		LinearLayout aHorizontalLayout = new LinearLayout(
				getApplicationContext());
		mMainLayout = new LinearLayout(getApplicationContext());
		mChildrenListLayout = new LinearLayout(getApplicationContext());

		mMainLayout.setLayoutParams(BOTH_FILL_PARENT);
		mMainLayout.setOrientation(LinearLayout.VERTICAL);

		mChildrenListLayout.setLayoutParams(BOTH_FILL_PARENT);
		mChildrenListLayout.setOrientation(LinearLayout.VERTICAL);

		aHorizontalLayout.setLayoutParams(new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		aHorizontalLayout.setOrientation(LinearLayout.HORIZONTAL);

		mAddChildButton = new Button(getApplicationContext());
		mAddChildButton.setOnClickListener(this);
		mAddChildButton.setText("Add Child");

		aHorizontalLayout.addView(mAddChildButton);

		mMainLayout.addView(aHorizontalLayout);
		mMainLayout.addView(mChildrenListLayout);
	}

	private void updateChildren() {
		mChildrenListLayout.removeAllViews();
		for (int i = 0; i < mAllChildren.length; i++) {
			mChildrenListLayout.addView(new ChildTextView(
					getApplicationContext(), mAllChildren[i]));
		}
	}

	public void onClick(View v) {
		Intent i = new Intent(HomeScreen.this, NewChildActivity.class);
		startActivity(i);
	}

	public String toString() {
		return getClass().getSimpleName();
	}
}
