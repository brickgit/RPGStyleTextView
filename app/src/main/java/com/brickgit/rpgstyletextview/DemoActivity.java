package com.brickgit.rpgstyletextview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DemoActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo);

		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				RPGStyleTextView text = (RPGStyleTextView) findViewById(R.id.text);
				text.setText(getString(R.string.demo_text));
				text.show();
			}
		});
	}
}
