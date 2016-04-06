package net.adheringsoft.coffeeonline.viewflow;



import neusoft.soft.coffeestore.view.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 *
 * @ClassName:DetailActivity
 * @Description:TODO
 * @author fulimei
 * @date 2014-12-11下午10:45:42
 *
 */
public class DetailActivity extends Activity {

	private ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		imageView = (ImageView)findViewById(R.id.iv_detailImage);

		Bundle extras = getIntent().getExtras();
		imageView.setImageResource(extras.getInt("image_id"));
	}

}
