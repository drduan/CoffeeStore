package neusoft.soft.coffeestore.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import neusoft.soft.coffeestore.view.R;
import android.app.Activity;


public class FragmentMeDataCtrl {
	private static ArrayList<HashMap<String, Object>> list;

	public static ArrayList<HashMap<String, Object>> getFunctionsName() {
		list = new ArrayList<HashMap<String, Object>>();
		addFunctionsName("我的信息", R.drawable.icon_more_context);
		addFunctionsName("我的评论", R.drawable.icon_more_context);
		addFunctionsName("地址管理", R.drawable.icon_more_context);
		addFunctionsName("我的收藏", R.drawable.icon_more_context);
		addFunctionsName("我的礼券", R.drawable.icon_more_context);
		addFunctionsName("我的积分", R.drawable.icon_more_context);
		addFunctionsName("意见反馈", R.drawable.icon_more_context);
		addFunctionsName("客服热线", R.drawable.icon_more_context);
		return list;
	}

	public static void addFunctionsName(String name, int image) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("functionName", name);
		map.put("iconImage", image);
		list.add(map);
	}
}
