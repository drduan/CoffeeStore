package neusoft.soft.coffeestore.fragment;

import neusoft.soft.coffeestore.view.CoffeeActivity;
import neusoft.soft.coffeestore.view.R;
import neusoft.soft.coffeestore.view.ShopActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;


public class ShopCarFragment extends Fragment {
	
	public ShopCarFragment() {
		
	}
	private Button btnButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		 View view=inflater.inflate(R.layout.fragment_shop_car, container, false);
		 btnButton=(Button)view.findViewById(R.id.btn_go_home);
		 btnButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(ShopCarFragment.this.getActivity(),CoffeeActivity.class);
				startActivity(intent);
			}
		});
	     return view;
	}

	
	
}
