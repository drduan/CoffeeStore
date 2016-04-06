package neusoft.soft.coffeestore.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import neusoft.soft.coffeestore.bean.Shop;
import neusoft.soft.coffeestore.db.DBUtil;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ShopActivity extends Activity{
	List<HashMap<String,String>> data;
	final String DB_DIR = "databases"; 
	final String DB_NAME = "coffeeshop"; 
	private  ListView list;
	ApplicationInfo applicationInfo;
	String databasePath;	
	DBUtil dbUtil;	
	 Shop[] shops;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_layout);
        list=(ListView)findViewById(R.id.listshop);
        init(this); 
        dbUtil = DBUtil.getInstance(databasePath);		
		dbUtil.openDB();
		showAllShops();  
		registerForContextMenu(list);
    }
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		showAllShops();
	}
	public  void showAllShops(){
		shops=dbUtil.queryAllShop();		
	   data=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<shops.length;i++){
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("shop_name",shops[i].getShop_name());
			map.put("shop_address",shops[i].getShop_address());
			map.put("shop_tel", shops[i].getTel());
			String picName=shops[i].getImg_name();
			int picId=getResources().getIdentifier(picName, "drawable" , ShopActivity.this.getPackageName());
			map.put("img_id", picId+"");
			data.add(map);				
			
		}
		MyAdapter adapter=new MyAdapter
		(ShopActivity.this, data, R.layout.list_item_custom,
		new String[]{"shop_name","shop_address","shop_tel","img_id"}, 
		new int[]{R.id.txtName,R.id.txtAddress,R.id.txtTel,R.id.img});
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Intent intent=new Intent();
				intent.setClass(ShopActivity.this,ShopDetailActivity.class);
				Bundle bundle=new Bundle();
				bundle.putSerializable("shop", shops[position]);
				intent.putExtras(bundle);
				startActivity(intent);
				
				
			}
		});
	}
	class MyAdapter extends SimpleAdapter{

		public MyAdapter(Context context, List<? extends Map<String, ?>> data,
				int resource, String[] from, int[] to) {
			super(context, data, resource, from, to);
		
		}
		@Override
		public View getView(int position, View convertView, 
				ViewGroup parent)
		{
			 View result=super.getView(position, convertView, parent);
			 TextView txtTilte=(TextView)result.findViewById(R.id.txtName);
		if(position%2==1){
			result.setBackgroundColor(Color.GREEN);
			txtTilte.setTextColor(Color.BLUE);
		}
			else{
				result.setBackgroundColor(Color.YELLOW);
				txtTilte.setTextColor(Color.RED);
			}

			 return result;
		}
		
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
 public void onCreateContextMenu(ContextMenu menu, View v,  
         ContextMenuInfo menuInfo) {  
 	
     MenuInflater mInflater = getMenuInflater();  
     mInflater.inflate(R.menu.shop, menu);  
       
     super.onCreateContextMenu(menu, v, menuInfo);  
 }  

 /**  
  * 当菜单某个选项被点击时调用该方法    
  */  
 @Override  
 public boolean onContextItemSelected(MenuItem item) { 
 	AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
             .getMenuInfo(); 
 	

 	final int id = (int) info.id;
     switch(item.getItemId()){  
     case R.id.update:  
    	 Intent intent=new Intent();
			intent.setClass(ShopActivity.this,UpdateShopActivity.class);
			Bundle bundle=new Bundle();
			bundle.putSerializable("shop", shops[id]);
			intent.putExtras(bundle);
			startActivity(intent);
         break;
     case R.id.add:  
    	 Intent intent1=new Intent();
			intent1.setClass(ShopActivity.this,AddShopActivity.class);
			/*Bundle bundle2=new Bundle();
			bundle2.putString("shop_id", shops[id].getShop_id());
			intent1.putExtras(bundle2);*/
			intent1.putExtra("shop_id", shops[id].getShop_id());
			startActivity(intent1);
         break;
     case R.id.delete:  
     	new AlertDialog.Builder(ShopActivity.this).setMessage("确定删除？" )  
     	.setPositiveButton("否" ,  new DialogInterface.OnClickListener(){
     	
     	public void onClick(DialogInterface dialog, int which) { 
     	}         
         })  
     	.setNegativeButton("是" , new DialogInterface.OnClickListener(){  
             @Override  
             public void onClick(DialogInterface dialog, int which) {
             	
             	
         		String shop_id = shops[id].getShop_id();
 				dbUtil.deleteOneData(shop_id);
 				Intent intent = new Intent();
 				intent.setClass(ShopActivity.this, ShopActivity.class);
 				startActivity(intent);
                 }
             })
     	.show(); 
         break;
  
     }  
     return super.onContextItemSelected(item);  
 }  

   
    
}
