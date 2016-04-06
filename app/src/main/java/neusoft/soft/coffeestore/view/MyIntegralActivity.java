package neusoft.soft.coffeestore.view;
import neusoft.soft.coffeestore.view.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
public class MyIntegralActivity extends Activity {
	private Button btnBack;
	private Button btnExchange;
	private TextView tvTitle;
	
	private Button btnSignIn;
	private TextView tvDaysCount;
	private int days = 3;
	
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.CustomTheme);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_integral);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
		
		btnBack = (Button) findViewById(R.id.btn_first);
		btnExchange = (Button) findViewById(R.id.btn_second);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		btnSignIn = (Button) findViewById(R.id.btn_sign_in);
		tvDaysCount = (TextView) findViewById(R.id.tv_days_count);
		
		btnBack.setBackgroundResource(R.drawable.img_back);
		btnExchange.setText("兑换");
		tvTitle.setText("我的积分");
		
		btnBack.setOnClickListener(new ButtonBackOnClickListener());
		btnSignIn.setOnClickListener(new ButtonSignInClickListener());
		tvDaysCount.setText("亲，您已累计签到"+ days +"天了哦！");
	};
	
	class ButtonSignInClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			tvDaysCount.setText("亲，您已累计签到"+ (days+1) +"天了哦！");
			btnSignIn.setText("明日再来");
			btnSignIn.setClickable(false);
		}
	}
	
	class ButtonBackOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MyIntegralActivity.this, MainActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.back_out, R.anim.back_in);
		}
	}
	
}

