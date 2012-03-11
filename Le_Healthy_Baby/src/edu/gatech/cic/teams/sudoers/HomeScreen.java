package edu.gatech.cic.teams.sudoers;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeScreen extends Activity {
	/** Called when the activity is first created. */
	private Button mAddChildButton;
	private LinearLayout mMainLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLayout();
		Child[] allChildren = getChildren();
		TextView temp;
		LinearLayout aHorizontalLayout;
		for (int i = 0; i < allChildren.length; i++) {
			aHorizontalLayout = new LinearLayout(this.getApplicationContext());
			aHorizontalLayout.setLayoutParams(new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			aHorizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
			temp = new TextView(getApplicationContext());
			temp.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT));
			temp.setText(allChildren[i].getName());
			aHorizontalLayout.addView(temp);
			mMainLayout.addView(aHorizontalLayout);
		}
		setContentView(mMainLayout);

	}

	private Child[] getChildren() {
		DatabaseOpenHelper dbs = DatabaseFactory.getDatabaseHelper();
		SQLiteDatabase db = dbs.getWritableDatabase();
		db = dbs.getWritableDatabase();
		Cursor c = db.rawQuery("SELECT " + DatabaseOpenHelper.CHILD_ID
				+ " as _id, " + DatabaseOpenHelper.CHILD_NAME + " FROM "
				+ DatabaseOpenHelper.CHILDREN_TABLE_NAME, new String[] {});
		Child[] allChildren = (Child[]) new Object[c.getCount()];
		c.moveToFirst();
		Child tempChild;
		for (int i = 0; i < allChildren.length; i++) {
			tempChild = new Child();
			tempChild.setName(c.getString(c
					.getColumnIndex(DatabaseOpenHelper.CHILD_NAME)));
			allChildren[i] = tempChild;
		}
		return allChildren;
	}

	private void initLayout() {
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
		mAddChildButton.setOnClickListener(new AButtonListener());
		mAddChildButton.setText("Add Child");
		aHorizontalLayout.addView(mAddChildButton);
		mMainLayout.addView(aHorizontalLayout);
	}

	class AButtonListener implements OnClickListener {
		public void onClick(View v) {
			Intent i = new Intent(HomeScreen.this, NewChildActivity.class);
			startActivity(i);
		}
	}
}