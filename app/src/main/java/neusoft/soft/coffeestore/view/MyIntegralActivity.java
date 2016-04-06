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
		btnExchange.setText("�һ�");
		tvTitle.setText("�ҵĻ���");
		
		btnBack.setOnClickListener(new ButtonBackOnClickListener());
		btnSignIn.setOnClickListener(new ButtonSignInClickListener());
		tvDaysCount.setText("�ף������ۼ�ǩ��"+ days +"����Ŷ��");
	};
	
	class ButtonSignInClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			tvDaysCount.setText("�ף������ۼ�ǩ��"+ (days+1) +"����Ŷ��");
			btnSignIn.setText("��������");
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

