package neusoft.soft.coffeestore.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.os.Build;
/**此页面用来做Listview操作的测试 */
public class CoffeeActivity extends Activity {
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==4 ){//按下“返回”按键
			android.os.Process.killProcess(android.os.Process.myPid());//让程序完全退出应用
		}
		return super.onKeyDown(keyCode, event);
	}
    private ListView mListView;
    private ArrayList<HashMap<String,Object>> maplist;
    private HashMap<String,Object> map;
    private RatingBar ratingBar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mListView = (ListView) findViewById(R.id.lv_list);
        getAdapter();
    }
    public void input(int image,String name,String info,int price,float bar){
    	map = new HashMap<String,Object>();
    	map.put("image",image );
    	map.put("name",name );
    	map.put("info",info );
    	map.put("price",price );
    	map.put("bar", bar);
    	maplist.add(map);
    }
    public ArrayList<HashMap<String,Object>> getSortList(ArrayList<HashMap<String,Object>> maplist){
    	for (int j= 0; j< maplist.size()-1; j++) {
			for (int i = 0; i< maplist.size()-1-j; i++) {

				float bar = (Float) maplist.get(i).get("bar");
				float bar1 = (Float) maplist.get(i+1).get("bar");
				if (bar >= bar1) {
					
				} else {
					HashMap<String, Object> theList=maplist.get(i+1);
					maplist.remove(i+1);
					maplist.add(i, theList);
				}
			}
		}
		return maplist;
    	
    }
    public void getAdapter(){
    	// 构建数据
    	maplist = new ArrayList<HashMap<String,Object>>();
    	input(R.drawable.a1, "维也纳咖啡", "不加搅拌，开始是凉奶油，感觉很舒服，然后喝到热咖啡，最后感觉出砂糖的甜味，有着三种不同的口感", 23,1f);
    	input(R.drawable.a2,"摩加咖啡","由摩回咖啡和加奶油块咖啡混合而成",45,2f); 
    	input(R.drawable.a3,"卡普奇诺","伴有肉桂棒，再淋上柠檬汁，显示出复杂的风味",65,3f);
    	input(R.drawable.a4, "椰子汁·加奶油块的咖啡", "带有椰子芳香味的香味咖啡。椰子的香味很强烈", 34,4f);
    	input(R.drawable.a5, "摩加薄荷咖啡", "冷奶油浮起，成冷甜奶油，它下面的咖啡是热的", 38,5f);
    	input(R.drawable.a6, "玛奇朵咖啡", "其做法是在牛奶中加入香草糖浆，与Espresso咖啡混合", 38,5f);
    	input(R.drawable.a7, "白咖啡", "布雷维是Espresso咖啡+热的鲜奶油，表面有奶泡", 38,1f);
    	input(R.drawable.a8, "爱尔兰咖啡", "把风味独到的特制 Espresso 佐以威士忌、糖和鲜奶油,调和出香滑顺口、甘苦适中的滋味", 38,2f);
    	input(R.drawable.a9, "玛琪雅朵", "只要在咖啡上添加两大匙绵密细软的奶泡，如此就是一杯玛琪雅朵", 38,3f);
    	input(R.drawable.a10, "康宝兰", "搅拌奶油既可以搅和在咖啡里，也可以当作小点心另外上，供宾客边吃边饮", 38,4f);
    	
    	/*SimpleAdapter adapter = new SimpleAdapter(
    			MainActivity.this,
    			maplist,
    			R.layout.listview_layout,
    			new String[]{"image", "name", "info","price"},
    			new int[]{R.id.iv_image, R.id.tv_name, R.id.tv_info,R.id.tv_price});*/
    	
    	
    	BaseAdapter adapter = new NewAdapter(this,getSortList(maplist));
    	/*adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data,
                    String textRepresentation) {
                if ((view instanceof RatingBar) && (data instanceof Float)) {
                    ratingBar = (RatingBar) view;
                    ratingBar.setRating((Float) data);
                    return true;
                }
                return false;
            }
        });*/
    	
    	mListView.setAdapter(adapter);
    	
    	
    	
    	mListView.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Toast.makeText(
						CoffeeActivity.this, 
						"你想要喝 " + maplist.get(position).get("name")+"？想得美！", 
						Toast.LENGTH_LONG).show();
			}
    		
    	});
    	
    	
    }
    
    private class NewAdapter extends BaseAdapter{
    	Context context;
    	ArrayList<HashMap<String,Object>> maplist2;
		public NewAdapter(Context context2,ArrayList<HashMap<String,Object>> maplist){
			context = context2;
			maplist2 = maplist;
		}
		public int getCount() {
			// TODO Auto-generated method stub
			return maplist2.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return maplist2.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder vh;
			if(convertView==null){
				final LayoutInflater inflater = LayoutInflater.from(context);
				convertView = inflater.inflate(R.layout.listview_layout, null);
				vh = new ViewHolder();
				
				vh.imageView = (ImageView) convertView.findViewById(R.id.iv_image);
				vh.name = (TextView) convertView.findViewById(R.id.tv_name);
				vh.info = (TextView) convertView.findViewById(R.id.tv_info);
				vh.price = (TextView) convertView.findViewById(R.id.tv_price);
				vh.ratbar = (RatingBar) convertView.findViewById(R.id.ratbar);
				convertView.setTag(vh);
			}else{
				vh = (ViewHolder) convertView.getTag();
			}
			vh.imageView.setImageResource((Integer) maplist2.get(position).get("image"));
			vh.name.setText(maplist2.get(position).get("name")+"");
			vh.info.setText(maplist2.get(position).get("info")+"");
			vh.price.setText(maplist2.get(position).get("price")+"");
			vh.ratbar.setRating((Float) maplist2.get(position).get("bar"));
			return convertView;
		}
		class ViewHolder{
			 ImageView imageView;
			 TextView name;
			 TextView info;
			 TextView price;
			 RatingBar ratbar;
		}
    }
    
    }
   /* private class MySimpleAdapter extends SimpleAdapter{

		public MySimpleAdapter(Context context,
				List<? extends Map<String, ?>> data, int resource,
				String[] from, int[] to) {
			super(context, data, resource, from, to);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View result = super.getView(position, convertView, parent);
			
			TextView txt = (TextView)(result.findViewById(R.id.tv_name));
			RatingBar rbar = (RatingBar) result.findViewById(R.id.ratbar);
			rbar.setRating((Float) maplist.get(position).get("bar"));
			switch (position) {
			case 0: rbar.setRating(1);break;
			case 1: rbar.setRating(2);break;
			case 2: rbar.setRating(3);break;
			case 3: rbar.setRating(4);break;
			case 4: rbar.setRating(5);break;
			case 5: rbar.setRating(4);break;
			case 6: rbar.setRating(3);break;
			case 7: rbar.setRating(2);break;
			case 8: rbar.setRating(1);break;
			case 9: rbar.setRating(3);break;
			case 10: rbar.setRating(4);break;
			
			}
			if (position % 2 == 1){
				result.setBackgroundColor(Color.argb(255, 200, 200, 200));
				txt.setTextColor(Color.BLACK);
				rbar.setRating(4);
			}
			else {
				result.setBackgroundColor(Color.WHITE);
				txt.setTextColor(Color.RED);
			}
			
			HashMap<String,Object> currentObject = maplist.get(position);
			String title = currentObject.get("name").toString();
			if (title.equalsIgnoreCase("维也纳咖啡"))
			{
				txt.setTextColor(Color.BLUE);
			}
			return result;
		}*/
    	
		

