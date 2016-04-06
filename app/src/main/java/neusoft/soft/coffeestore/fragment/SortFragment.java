package neusoft.soft.coffeestore.fragment;

import neusoft.soft.coffeestore.view.R;
import neusoft.soft.coffeestore.view.R.layout;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SortFragment extends Fragment {
	
	
   @Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view= inflater.inflate(R.layout.fragment_sort, 
				container, false);
	    
		return view;
	}

	

}
