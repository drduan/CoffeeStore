package neusoft.soft.coffeestore.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

public class StartActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//该类主要是用来判断用户是不是第一次安装本app，如果是第一次安装需要经过三个viewfilper切换广告，如果不是的话就直接进入主页面跳过广告
		SharedPreferences sharedata = getSharedPreferences("config", 
				this.MODE_PRIVATE);
		String data = sharedata.getString("time","");
		if(data.equals("secondTime")){
			Intent intent2 = new Intent(this,MainActivity.class);
			startActivity(intent2);
		}else{
			SharedPreferences sp=this.getSharedPreferences("config",
					this.MODE_PRIVATE);
			Editor editor = sp.edit();
			editor.putString("time","secondTime");
			editor.commit();
			Intent intent1 = new Intent(this,Welcome.class);
			startActivity(intent1);
		}
	}
}
