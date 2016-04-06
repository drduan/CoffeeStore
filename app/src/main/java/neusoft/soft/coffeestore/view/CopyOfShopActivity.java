package neusoft.soft.coffeestore.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import neusoft.soft.coffeestore.bean.Shop;
import neusoft.soft.coffeestore.db.DBUtil;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
/**此页面用来做数据库操作的测试 ,包括图片的各种存取方法*/
public class CopyOfShopActivity extends Activity{
	final String DB_DIR = "databases"; 
	final String DB_NAME = "coffeeshop"; 
	
	ApplicationInfo applicationInfo;
	String databasePath;
	
	DBUtil dbUtil;
	
	Handler handler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			
			switch (msg.what) {
			case 1:
				
				dbUtil = DBUtil.getInstance(databasePath);
				
				dbUtil.openDB();
				break;

			default:
				break;
			}
		};
		
	};
	
//Int imageId=this.getResources()getIdentifiier(this.getPackageName+”:drawable/”+imageFileName);
	TextView shopInfoView;
	Button getShopButton;
	ImageView img;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.copy_shop_layout);

        init(this);
      
        
       initDB();
    }
    
private List<Shop> getShop(Cursor cursor){
	
	List<Shop> shops = null;
	if(cursor != null){
		
		shops = new ArrayList<Shop>();
		
		while (cursor.moveToNext()) {
			
			Shop s = new Shop();
			s.setShop_id(cursor.getString(0));
			s.setShop_name(cursor.getString(1));
			s.setShop_address(cursor.getString(2));
			s.setTel(cursor.getString(3));
			s.setImg_name(cursor.getString(4));
			s.setImg_id(cursor.getInt(5));
			shops.add(s);
		}
	}
	
	return shops;
}

    private void init(Context context){
    	
    	shopInfoView = (TextView)findViewById(R.id.txtResult);
    	getShopButton = (Button)findViewById(R.id.btnShop);
    	img=(ImageView)findViewById(R.id.img);
    	getShopButton.setOnClickListener(new OnClickListener() {
	   	
			@Override
			public void onClick(View v) {

				if(dbUtil != null){
					
					Shop shop = new Shop();
					shop.setShop_id("LN_DL_ZS_002");
					shop.setShop_name("左");
					Cursor cursor = dbUtil.getShopLike(shop);
					//Cursor cursor = dbUtil.getAllShop();
					List<Shop> shops = getShop(cursor);
					String text = "";
					for (Shop s : shops) {
						
						System.out.println(s.getShop_id());
						System.out.println(s.getShop_name());
						System.out.println(s.getShop_address());
						System.out.println(s.getTel());
						System.out.println("----------");
						//img.setBackgroundResource(s.getImg_id());
						String picName=s.getImg_name();
						//图片存在asset目录下，以流的的方式读取图片
						AssetManager assetManager=CopyOfShopActivity.this.getAssets();
						
						try {
							InputStream inputStream = assetManager.open(picName+".png");
							Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
							img.setImageBitmap(bitmap);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//图片存在res目录下，根据图片名称找图片的资源id
						/*int picId=getResources().getIdentifier(picName, "drawable" , CopyOfShopActivity.this.getPackageName());
						img.setBackgroundResource(picId);*/
						text += String.format("%s\n%s\n%s\n%s\n", s.getShop_id(), s.getShop_name(), s.getShop_address(), s.getTel());
						
					}
					shopInfoView.setText(text);
				}
			}
		});
    	
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
    
    private void initDB(){
    	
new Thread(new Runnable() {
	
	@Override
	public void run() {
		
		try {
			InputStream inputStream = getAssets().open("coffeeshop.sqlite");

					if(databasePath != null){
						
						File file = new File(databasePath);
						
						if(!file.exists()){
							file.createNewFile();
						}
						
						FileOutputStream outputStream = new FileOutputStream(file);
						
						byte[] buffer = new byte[1024 * 4];
						int count = 0;
						while( (count = inputStream.read(buffer)) != -1 ){
							
							outputStream.write(buffer, 0, count);
						}
						outputStream.close();
					}
					
					inputStream.close();
					
					handler.sendEmptyMessage(1);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
    }
}
