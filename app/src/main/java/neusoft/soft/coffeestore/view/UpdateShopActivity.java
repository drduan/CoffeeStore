package neusoft.soft.coffeestore.view;

import java.io.File;

import neusoft.soft.coffeestore.bean.Shop;
import neusoft.soft.coffeestore.db.DBUtil;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class UpdateShopActivity extends Activity {
	private Button btnUpdate;
	EditText name,address,number,img_name;
	private String  id;
	final String DB_DIR = "databases"; 
	final String DB_NAME = "coffeeshop"; 
	
	ApplicationInfo applicationInfo;
	String databasePath;	
	DBUtil dbUtil;	
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_layout);
		init(UpdateShopActivity.this);
		dbUtil = DBUtil.getInstance(databasePath);		
		dbUtil.openDB();		
		btnUpdate=(Button)this.findViewById(R.id.btnOK);
        name = (EditText) findViewById(R.id.name);		
		address = (EditText) findViewById(R.id.address);
		number = (EditText) findViewById(R.id.tel); 
		img_name=(EditText)findViewById(R.id.img_name);
		Intent intent = getIntent();
		 Shop shop=(Shop)intent.getSerializableExtra("shop");	
		id=shop.getShop_id();
		name.setText(shop.getShop_name());
		
		address.setText(shop.getShop_address());
		number.setText(shop.getTel());
		
		btnUpdate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Shop shop = new Shop();
				shop.setShop_address(address.getText().toString());
				shop.setTel(number.getText().toString());
				shop.setShop_name(name.getText().toString());
				shop.setImg_name(img_name.getText().toString());
				dbUtil.updateOneData(id, shop);
		        
				
			}
		});	
	}
      private void init(Context context){    	
    	
    	String packageName = context.getPackageName();
    	try {
			applicationInfo = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
			
			String dbDir = applicationInfo.dataDir + File.separator + DB_DIR;
			File file = new File(dbDir);
			if(!file.exists()){
				
				file.mkdir();
			}
			databasePath = applicationInfo.dataDir + File.separator + DB_DIR + File.separator + DB_NAME;
			
		} catch (NameNotFoundException e) {
			
		}
    	
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_shop, menu);
		return true;
	}
	public void back(View v){
		/*Intent intent = new Intent();
		intent.setClass(UpdateShopActivity.this, ShopActivity.class);
		startActivity(intent);*/
		finish();
	}	
}
