package net.adheringsoft.coffeeonline.viewflow;

import neusoft.soft.coffeestore.view.R;

import org.taptwo.android.widget.CircleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import android.app.Activity;
import android.os.Bundle;


public class ViewFlowTestActivity extends Activity {
    /** Called when the activity is first created. */
	private ViewFlow viewFlow;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
        viewFlow = (ViewFlow)findViewById(R.id.viewflow);
        viewFlow.setAdapter(new ImageAdapter(this));
        viewFlow.setmSideBuffer(3); 
        
        CircleFlowIndicator indic = (CircleFlowIndicator)
        		findViewById(R.id.viewflowindic);
        viewFlow.setFlowIndicator(indic);
        viewFlow.setTimeSpan(4500);
        viewFlow.setSelection(3*1000);	//设置初始位置
        viewFlow.startAutoFlowTimer();  //启动自动播放
        
    }
}