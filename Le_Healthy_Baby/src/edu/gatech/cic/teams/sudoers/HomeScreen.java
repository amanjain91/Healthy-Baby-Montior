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

public class HomeScreen extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	private Button mAddChildButton;
	private LinearLayout mMainLayout;
	private Child[] allChildren;
	private DatabaseOpenHelper mReadableWritableDatabase;
	private LinearLayout mChildrenListLayout;

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
		updateChildren();
		mMainLayout.invalidate();
	}

	private void getChildren() {
		if (mReadableWritableDatabase == null) {
			Log.v("HomeScreen", "DatabaseOpenHelper instance variable is null!");
		}
		SQLiteDatabase db = mReadableWritableDatabase.getReadableDatabase();
		Cursor c = db.query(DatabaseOpenHelper.CHILDREN_TABLE_NAME, null, null,
				null, null, null, null);
		c.moveToFirst();
		Child tempChild;
		allChildren = new Child[c.getCount()];
		for (int i = 0; i < allChildren.length; i++) {
			tempChild = new Child();
			tempChild.setName(c.getString(c
					.getColumnIndex(DatabaseOpenHelper.CHILD_NAME)));
			allChildren[i] = tempChild;
			c.moveToNext();
		}
	}

	private void initLayout() {
		mChildrenListLayout = new LinearLayout(getApplicationContext());
		mChildrenListLayout.setLayoutParams(new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		mChildrenListLayout.setOrientation(LinearLayout.VERTICAL);
		LinearLayout aHorizontalLayout = new LinearLayout(
				this.getApplicationContext());
		mMainLayout = new LinearLayout(getApplicationContext());
		mMainLayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		mMainLayout.setOrientation(LinearLayout.VERTICAL);
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
		for (int i = 0; i < allChildren.length; i++) {
			mChildrenListLayout.addView(new ChildTextView(
					getApplicationContext(), allChildren[i]));
		}
	}

	public void onClick(View v) {
		Intent i = new Intent(HomeScreen.this, NewChildActivity.class);
		startActivity(i);
	}
}
