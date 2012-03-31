package edu.gatech.cic.teams.sudoers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class DummyScreen extends Activity implements OnClickListener {
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

	public void onClick(View v) {
		// FIXME
		startActivity(new HeightDayChart().execute(getApplicationContext(),
				new Child(1, getApplicationContext())));
	}
}
