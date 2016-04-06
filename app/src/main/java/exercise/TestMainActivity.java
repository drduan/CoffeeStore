package exercise;

import neusoft.soft.coffeestore.view.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class TestMainActivity extends Activity implements OnGestureListener{
	private GestureDetector gestureDetector=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_main);
		gestureDetector=new GestureDetector(this);
		SharedPreferences sharedPreferences=this.getSharedPreferences("config",Context.MODE_PRIVATE);
		String timeString=sharedPreferences.getString("time", "");
		if(timeString.equals("second")){
			//调到主页
		}
		else{
			SharedPreferences sharedPreference=this.getSharedPreferences("config",Context.MODE_PRIVATE);
			SharedPreferences.Editor editor=sharedPreference.edit();
			editor.putString("time", "second");
			editor.commit();


		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return gestureDetector.onTouchEvent(event);
	}
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
							float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
						   float velocityY) {
		// TODO Auto-generated method stub

		return true;
	}



}
