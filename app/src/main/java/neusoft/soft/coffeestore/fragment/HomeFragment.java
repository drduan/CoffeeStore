package neusoft.soft.coffeestore.fragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.adheringsoft.coffeeonline.viewflow.ImageAdapter;
import neusoft.soft.coffeestore.bean.Coffee;
import neusoft.soft.coffeestore.bean.Shop;
import neusoft.soft.coffeestore.db.DBUtil;
import neusoft.soft.coffeestore.view.CoffeeActivity;
import neusoft.soft.coffeestore.view.R;
import neusoft.soft.coffeestore.view.ShopActivity;
import neusoft.soft.coffeestore.view.ShopDetailActivity;

import org.taptwo.android.widget.CircleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class HomeFragment extends Fragment {
	
	 
private ViewFlow viewFlow;
final String DB_NAME = "coffeeshop"; 
private  GridView grid;
ApplicationInfo applicationInfo;
String databasePath;

DBUtil dbUtil;
	
	private ArrayList<HashMap<String, Object>> list;
	private ListView lvCoffeeList;	
	private LinearLayout llModule1;
	private LinearLayout llModule2;
	private LinearLayout llModule3;
	private LinearLayout llModule4;
	private LinearLayout llModule5;
	private LinearLayout llModule6;
	private LinearLayout llModule7;
	private LinearLayout llModule8;
	
	@SuppressLint("InflateParams")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, null);
		grid=(GridView)view.findViewById(R.id.grid);
		viewFlow = (ViewFlow)view.findViewById(R.id.viewflow);
		// Adapt the image
        viewFlow.setAdapter(new ImageAdapter(HomeFragment.this.getActivity()));
        viewFlow.setmSideBuffer(3); 
        
        CircleFlowIndicator indic = (CircleFlowIndicator)view.findViewById(R.id.viewflowindic);
        viewFlow.setFlowIndicator(indic);
        viewFlow.setTimeSpan(3000);
        viewFlow.setSelection(3*1000);	// Set the initial position
        viewFlow.startAutoFlowTimer();  // To start the automatic broadcast
        
        //lvCoffeeList = (ListView) view.findViewById(R.id.lv_coffee_list);
        llModule1 = (LinearLayout) view.findViewById(R.id.ll_module1);
        llModule2 = (LinearLayout) view.findViewById(R.id.ll_module2);
        llModule3 = (LinearLayout) view.findViewById(R.id.ll_module3);
        llModule4 = (LinearLayout) view.findViewById(R.id.ll_module4);
        llModule5 = (LinearLayout) view.findViewById(R.id.ll_module5);
        llModule6 = (LinearLayout) view.findViewById(R.id.ll_module6);
        llModule7 = (LinearLayout) view.findViewById(R.id.ll_module7);
        llModule8 = (LinearLayout) view.findViewById(R.id.ll_module8);
        init(HomeFragment.this.getActivity());   
        initDB();      
        
        llModule1.setOnClickListener(new ModuleClickListener());
        llModule2.setOnClickListener(new ModuleClickListener());
        llModule3.setOnClickListener(new ModuleClickListener());
        llModule4.setOnClickListener(new ModuleClickListener());
        llModule5.setOnClickListener(new ModuleClickListener());
        llModule6.setOnClickListener(new ModuleClickListener());
        llModule7.setOnClickListener(new ModuleClickListener());
        llModule8.setOnClickListener(new ModuleClickListener());
        
		return view;
	}
	
	
	class ModuleClickListener implements OnClickListener {
		Intent intent=new Intent();
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ll_module1:
				
				Toast.makeText(getActivity(), "进入店铺", Toast.LENGTH_SHORT).show();
				intent.setClass(HomeFragment.this.getActivity(), ShopActivity.class);
				startActivity(intent);
				break;
			case R.id.ll_module2:
				Toast.makeText(getActivity(), "雀巢", Toast.LENGTH_SHORT).show();
				Intent intent2 = new Intent(HomeFragment.this.getActivity(), CoffeeActivity.class);
				startActivity(intent2);
				break;
			case R.id.ll_module3:
				Toast.makeText(getActivity(), "摩卡", Toast.LENGTH_SHORT).show();
				break;
			case R.id.ll_module4:
				Toast.makeText(getActivity(), "星巴克", Toast.LENGTH_SHORT).show();
				break;
			case R.id.ll_module5:
				Toast.makeText(getActivity(), "优惠卷", Toast.LENGTH_SHORT).show();
				break;
			case R.id.ll_module6:
				Toast.makeText(getActivity(), "我的关注", Toast.LENGTH_SHORT).show();
				break;
			case R.id.ll_module7:
				Toast.makeText(getActivity(), "赢大奖", Toast.LENGTH_SHORT).show();
				break;
			case R.id.ll_module8:
				Toast.makeText(getActivity(), "物流查询", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	}
	final String DB_DIR = "databases"; 
	
	
	Handler handler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			
			switch (msg.what) {
			case 1:
				
				dbUtil = DBUtil.getInstance(databasePath);
				
				dbUtil.openDB();
				showCommandCoffee();
				break;

			default:
				break;
			}
		};
		
	};
	
	
	public  void showCommandCoffee(){
		final Coffee[] coffees=dbUtil.queryAllCoffee();
		
		List<HashMap<String,String>> data=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<coffees.length;i++){
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("coffee_name",coffees[i].getCoffee_name());
			map.put("coffee_price",coffees[i].getCoffee_price()+"");
			map.put("coffee_intro", coffees[i].getCoffee_intro());
			map.put("coffee_com", coffees[i].getCoffee_com());
			String picName=coffees[i].getImage_name();
			int picId=getResources().getIdentifier(picName, "drawable" , HomeFragment.this.getActivity().getPackageName());
			map.put("img_id", picId+"");
			data.add(map);				
			
		}
		SimpleAdapter adapter=new SimpleAdapter
		(HomeFragment.this.getActivity(), data, R.layout.gridview_item_layout,
		new String[]{"coffee_name","coffee_price","img_id"}, 
		new int[]{R.id.txtName,R.id.txtPrice,R.id.img});
		grid.setAdapter(adapter);
		
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
    
    private void initDB(){
    	
new Thread(new Runnable() {
	
	@Override
	public void run() {
		
		try {
			InputStream inputStream =HomeFragment.this.getActivity(). getAssets().open("coffeeshop.sqlite");

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
