package net.adheringsoft.coffeeonline.viewflow;

import neusoft.soft.coffeestore.view.R;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;/**
 *
 * @ClassName:ImageAdapter
 * @Description:页面"首页"中的广告切换图片的适配器
 * @author fulimei
 * @date 2014-12-11下午10:44:43
 *
 */
public class ImageAdapter extends BaseAdapter {

	private Fragment mFragment;
	private Context mContext;
	private LayoutInflater mInflater;
	private static final int[] ids = {R.drawable.img_viewflow1, R.drawable.img_viewflow2, R.drawable.img_viewflow3 };

	public ImageAdapter(Context context) {
		mContext = context;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.image_item, null);
		}
		((ImageView) convertView.findViewById(R.id.imgView)).setImageResource(ids[position%ids.length]);
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext,DetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("image_id", ids[position%ids.length]);
				intent.putExtras(bundle);
				mContext.startActivity(intent);
			}
		});
		return convertView;
	}

}
