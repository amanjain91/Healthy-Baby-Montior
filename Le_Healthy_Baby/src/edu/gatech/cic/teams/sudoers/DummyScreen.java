package edu.gatech.cic.teams.sudoers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class DummyScreen extends Activity implements OnClickListener {
	private LengthDayChart ldc = new LengthDayChart();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout l = new LinearLayout(this.getApplicationContext());
		l.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		Button open = new Button(this.getApplicationContext());
		open.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		open.setOnClickListener(this);
		l.addView(open);
		setContentView(l);
	}

	public void onClick(View arg0) {
		Intent intent = ldc.execute(this.getApplicationContext());
		if (intent == null)
			Log.v("lol", "NOOOO!");
		startActivity(intent);
	}
}
