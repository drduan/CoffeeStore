package neusoft.soft.coffeestore.view;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CoffeeCultrueActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coffee_cultrue);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.coffee_cultrue, menu);
		return true;
	}

}
