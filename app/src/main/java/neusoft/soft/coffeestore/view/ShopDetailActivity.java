package neusoft.soft.coffeestore.view;

import neusoft.soft.coffeestore.view.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import neusoft.soft.coffeestore.bean.*;
public class ShopDetailActivity extends Activity {
private TextView txtInfo;
private Shop shop;
private ImageView img;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_detail);
		txtInfo=(TextView)findViewById(R.id.txtdetail);
		img=(ImageView)findViewById(R.id.image);
		Intent intent=this.getIntent();		
        shop=(Shop)intent.getSerializableExtra("shop");	
        txtInfo.setTextColor(Color.RED);
        txtInfo.setTextSize(20);
		txtInfo.setText("µÍ∆Ã√˚≥∆£∫"+shop.getShop_name()+"\n"
				+"µÍ∆Ãµÿ÷∑£∫"+shop.getShop_address()+"\n"+
				"µÍ∆ÃµÁª∞£∫"+shop.getTel());
		int picId=getResources().getIdentifier(shop.getImg_name(), "drawable" , ShopDetailActivity.this.getPackageName());
		img.setBackgroundResource(picId);
	}

	

}
