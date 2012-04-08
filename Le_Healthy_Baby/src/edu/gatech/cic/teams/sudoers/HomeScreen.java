/** Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php */
package edu.gatech.cic.teams.sudoers;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * This screen lists children as TextViews and on clicking then leads to their
 * notification streams.
 * 
 * @author Suren_Nihalani
 * @version 1.0
 */
public class HomeScreen extends ListActivity implements OnClickListener {
	/** The children to be drawn as a list on the screen */
	private Child[] mAllChildren;

	/** The database open helper used by everyone in this activity. */
	private DatabaseOpenHelper mReadableWritableDatabase;

	/** The cursor object to be used for every query result. */
	private Cursor mCursor;

	/**
	 * The method called on creation of this activity.
	 * 
	 * @param savedInstanceState
	 *            The bundle passed by the framework.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mReadableWritableDatabase = new DatabaseOpenHelper(
				getApplicationContext());
		mReadableWritableDatabase.onUpgrade(
				mReadableWritableDatabase.getWritableDatabase(),
				DatabaseOpenHelper.DATABASE_VERSION,
				DatabaseOpenHelper.DATABASE_VERSION);
		mReadableWritableDatabase.onCreate(mReadableWritableDatabase
				.getWritableDatabase());
		initLayout();
		getChildren();
		updateChildren();
	}

	/**
	 * The method called when the activities over this activity are done.
	 */
	public void onResume() {
		super.onResume();
		Log.v(getClass().getSimpleName(), "onResume() called.");
		getChildren();
		updateChildren();
	}

	/**
	 * Populates the children from the database into the internal data
	 * structure.
	 */
	private void getChildren() {
		SQLiteDatabase db = null;
		try {
			db = mReadableWritableDatabase.getReadableDatabase();
			mCursor = db.query(DatabaseOpenHelper.CHILDREN_TABLE_NAME, null,
					null, null, null, null, null);
			mCursor.moveToFirst();
			mAllChildren = new Child[mCursor.getCount()];
			for (int i = 0; i < mAllChildren.length; i++) {
				mAllChildren[i] = new Child(i + 1, getApplicationContext());
				mCursor.moveToNext();
			}
			db.close();
		} finally {
			if (db.isOpen())
				db.close();
		}
	}

	/**
	 * Initializes the bare minimum layout for the screen.<br>
	 * Context View hasn't been set yet.
	 */
	private void initLayout() {
		getListView().addHeaderView(
				View.inflate(this, R.layout.list_view_header, null));
	}

	/**
	 * Updates the view from
	 */
	private void updateChildren() {
		String[] temp = new String[mAllChildren.length];
		for (int i = 0; i < mAllChildren.length; i++) {
			temp[i] = mAllChildren[i].getName();
		}
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, temp));
		getListView().invalidate();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Log.v(this.getLocalClassName(), "Position: " + position + " Child: "
				+ mAllChildren[position - 1].getName());
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), ChildScreenT.class);
		intent.putExtra("childId", mAllChildren[position - 1].getChildId());
		intent.putExtra("childName", mAllChildren[position - 1].getName());
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	/**
	 * Called when a view has been clicked.
	 * 
	 * @param v
	 *            The view that was clicked.
	 * @see android.view.View$OnClickListener#onClick(View)
	 */
	public void onClick(View v) {
		startActivity(new Intent(HomeScreen.this, NewChildActivity.class));
	}

	/**
	 * Returns a string containing a concise, human-readable description of this
	 * object which in this case is the class name.
	 * 
	 * 
	 * @return The class name.
	 */
	public String toString() {
		return getClass().getSimpleName();
	}
}
