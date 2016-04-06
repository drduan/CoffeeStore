package neusoft.soft.coffeestore.view;

import java.net.URI;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;



public class FeedbackActivity extends Activity {
	private Button btnBack;
	private Button btnCommit;
	private TextView tvTitle;
	
	private Spinner sFeedbackType;
	private EditText etFeedbackContent;
	private EditText etFeedbackCommuni;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.CustomTheme);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_feedback);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
		
		btnBack = (Button) findViewById(R.id.btn_first);
		btnCommit = (Button) findViewById(R.id.btn_second);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		sFeedbackType = (Spinner) findViewById(R.id.s_feedback_type);
		etFeedbackContent = (EditText) findViewById(R.id.et_content);
		etFeedbackCommuni = (EditText) findViewById(R.id.et_communication);
		
		String[] mItems = getResources().getStringArray(R.array.spinnername);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.drop_down_item, mItems);
		sFeedbackType.setAdapter(adapter);
		
		btnBack.setBackgroundResource(R.drawable.img_back);
		tvTitle.setText(R.string.feedback);
		btnCommit.setText(R.string.submit);
		
		btnBack.setOnClickListener(new ButtonBackClickListener());
		
	}
	
	
	class ButtonBackClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(FeedbackActivity.this, MainActivity.class);
			intent.putExtra("fragmentId", 4);
			startActivity(intent);
			overridePendingTransition(R.anim.back_out, R.anim.back_in);
		}
	}
	
	
	
}
