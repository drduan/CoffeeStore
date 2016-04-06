package neusoft.soft.coffeestore.view;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import neusoft.soft.coffeestore.view.R;
public class LoginActivity extends Activity {
	private TextView tvTitle;
	private TextView tvForgetPwd;
	private TextView tvNewUser;
	private Button btnBack;
	private Button btnLogin;
	private Button btnRegister;
	private EditText etUsername;
	private EditText etPassword;
	
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
				
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
				startActivity(intent);
				break;

			default:
				break;
			}
			
		};
	};
	public boolean onMenuItemSelected(int featureId, android.view.MenuItem item) 
	{
		 switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;

		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.CustomTheme);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_login);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
		/*ActionBar actionBar=this.getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);*/
		tvTitle = (TextView) findViewById(R.id.tv_title);
		btnBack = (Button) findViewById(R.id.btn_first);
		btnLogin = (Button) findViewById(R.id.btn_login);
		btnRegister = (Button) findViewById(R.id.btn_second);
		tvForgetPwd = (TextView) findViewById(R.id.tv_forgetPassword);
		tvNewUser = (TextView) findViewById(R.id.tv_newUser);
		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
		
		tvTitle.setText("登录");
		btnBack.setBackgroundResource(R.drawable.img_back);
		btnRegister.setText("注册");
		
		//实例化登录页面的监听器对象
		LoginOnClickListener loginOnClickListener = new LoginOnClickListener();
		//为可能点击的按钮绑定监听器
		btnBack.setOnClickListener(loginOnClickListener); //为【返回按钮】绑定监听器对象
		btnLogin.setOnClickListener(loginOnClickListener); //为【登录按钮】绑定监听器对象
		btnRegister.setOnClickListener(loginOnClickListener); //为【注册按钮】绑定监听器对象
		tvForgetPwd.setOnClickListener(loginOnClickListener); //为【忘记密码文本域】绑定监听器对象
		tvNewUser.setOnClickListener(loginOnClickListener); //为【新用户文本域】绑定监听器对象
		
	}

	/**
	 * 登录页面中的监听器内部类
	 * Created by Fulimei on 2015年7月20日
	 * @version 1.0
	 */
	class LoginOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			//控件被点击后执行的代码
			if(v.getId() == R.id.btn_first) { //返回上一级的按钮被单击
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.down_out, R.anim.down_in);
			}
			else if(v.getId() == R.id.btn_second || v.getId() == R.id.tv_newUser) { //用户注册的按钮或新用户的文本域被单击
				Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.go_in, R.anim.go_out);
			}
			else if(v.getId() == R.id.tv_forgetPassword) { //忘记密码的文本域被单击
				Intent intent = new Intent(LoginActivity.this, ForgetPwdActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.go_in, R.anim.go_out);
			}
			else if(v.getId() == R.id.btn_login) { //用户登录的按钮被单击
				final String userName = etUsername.getText().toString().trim();
				final String password = etPassword.getText().toString().trim();
				if(userName.equals("") || password.equals("")){ //用户名或者密码未填写
					Toast.makeText(getApplicationContext(), "请将用户名密码填写完全后再登录", Toast.LENGTH_LONG).show();
				}
				else{
					//用户名密码均已正确填写
					//模拟登录过程
					final ProgressDialog pd=new ProgressDialog(LoginActivity.this);
					pd.setMessage("正在登录...");
					pd.show();
					new Thread() {
						public void run() {
							
							try {
								Thread.sleep(3000);
								
								handler.sendEmptyMessage(1);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							pd.dismiss();
						};
					}.start();
			
					/*new Thread() {
						public void run() {
							final String result = LoginService.loginByClientPost(userName, password);
							if(result != null) {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										Toast.makeText(LoginActivity.this, result, Toast.LENGTH_LONG).show();
									}
								});
								Intent intent = new Intent(LoginActivity.this, MainTabActivity.class);
								startActivity(intent);
							}
							else{
								//请求失败
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										Toast.makeText(LoginActivity.this, "请求超时...", Toast.LENGTH_LONG).show();
									}
								});
							}
						};
					}.start();*/
					
					//通过android-async-http-1.4.6框架完成与服务器端的交互
					//1.GET提交方式
					/*AsyncHttpClient client = new AsyncHttpClient();
					String path = "http://172.21.106.59:8080/CoffeeOnlineServer/servlet/LoginServlet?userName="
							+ URLEncoder.encode(userName) + "&password=" + URLEncoder.encode(password);
					client.get(path, new AsyncHttpResponseHandler() {
						
						@Override
						public void onSuccess(int arg0, Header[] arg1, byte[] response) {
							Toast.makeText(LoginActivity.this, "请求成功:"+new String(response), Toast.LENGTH_LONG).show();
						}
						
						@Override
						public void onFailure(int arg0, Header[] arg1, byte[] response, Throwable arg3) {
							Toast.makeText(LoginActivity.this, "请求失败:"+new String(response), Toast.LENGTH_LONG).show();
						}
					});*/
					//2.POST提交方式
					/*AsyncHttpClient client = new AsyncHttpClient();
					String url = IPProvider.IP_ADDRESS+"CoffeeOnlineServer/servlet/LoginServlet";
					RequestParams params = new RequestParams();
					params.put("userName", userName);
					params.put("password", password);
					client.post(url, params, new AsyncHttpResponseHandler() {
						@Override
						public void onSuccess(int arg0, Header[] arg1, byte[] response) {
							Toast.makeText(LoginActivity.this, "请求成功:"+new String(response), Toast.LENGTH_LONG).show();
						}
						@Override
						public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
							Toast.makeText(LoginActivity.this, "请求失败:"+arg3.getMessage(), Toast.LENGTH_LONG).show();
						}
					});*/
				}
			}
		}
	}

}
