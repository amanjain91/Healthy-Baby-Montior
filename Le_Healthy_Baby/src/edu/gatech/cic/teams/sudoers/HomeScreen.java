package edu.gatech.cic.teams.sudoers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeScreen extends Activity {
	/** Called when the activity is first created. */
	private Button mAddChildButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homescreen_layout);
		mAddChildButton = (Button) this.findViewById(R.id.button_add_child);
		mAddChildButton.setOnClickListener(new AButtonListener());
	}

	class AButtonListener implements OnClickListener {
		public void onClick(View v) {
			Intent i = new Intent(HomeScreen.this, NewChildActivity.class);
			startActivity(i);
		}
	}
}
