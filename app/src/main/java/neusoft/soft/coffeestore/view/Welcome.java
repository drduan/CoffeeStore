package neusoft.soft.coffeestore.view;
import android.app.Activity;  
import android.content.Intent;
import android.os.Bundle;  
import android.os.Handler;
import android.view.GestureDetector;  
import android.view.MotionEvent;  
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;  
import android.view.animation.Animation;  
import android.view.animation.AnimationUtils;  
import android.widget.ImageView;  
import android.widget.Toast;
import android.widget.ViewFlipper;  
  
public class Welcome extends Activity implements android.view.GestureDetector.OnGestureListener {  
    private int[] imgs = { R.drawable.coffe1, 
    		R.drawable.coffee2
                    };  
  //这个Test2类是演示带有动画的
    private GestureDetector gestureDetector = null;  
    private ViewFlipper viewFlipper = null;  
  
    private Activity mActivity = null;  
  
    @SuppressWarnings("deprecation")
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);
        
        final Window win = getWindow();//返回当前Activity的Window对象,Window类中概括了Android窗口的基本属性和基本功能
		//隐藏状态栏
		win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        setContentView(R.layout.viewfilter);  
  
        mActivity = this;  
      
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);  
        gestureDetector = new GestureDetector(this);    // 声明检测手势事件  
  
        for (int i = 0; i < imgs.length; i++) {          // 添加图片源  
            ImageView iv = new ImageView(this);  
            iv.setImageResource(imgs[i]);  
            iv.setScaleType(ImageView.ScaleType.FIT_XY);  
            viewFlipper.addView(iv);  
        }        
    }  
  
    @Override  
    public boolean onTouchEvent(MotionEvent event) {  
        //必须对onTouchEvent()重写，将触摸事件交给手势处理
        return gestureDetector.onTouchEvent(event);         // 注册手势事件  
    }  
  
    @Override  
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {  
        if (e2.getX() - e1.getX() > 120) {            // 从左向右滑动（左进右出）  
            
            //Toast.makeText(Test2.this, viewFlipper.getDisplayedChild()+"|>"+R.drawable.coffee3,0).show();
            if(viewFlipper.getDisplayedChild()==0){
                new Handler().postDelayed(new Runnable() {
  					
  					@Override
  					public void run() {
  						
 					
 					 Intent intent = new Intent(Welcome.this,MainActivity.class);
 	                startActivity(intent);
 	 				overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
  					}
  				}, 1000);}
                Animation rInAnim = AnimationUtils.loadAnimation(mActivity, R.anim.push_right_in);  // 向右滑动左侧进入的渐变效果（alpha  0.1 -> 1.0）  
                Animation rOutAnim = AnimationUtils.loadAnimation(mActivity, R.anim.push_right_out); // 向右滑动右侧滑出的渐变效果（alpha 1.0  -> 0.1）  
      
                viewFlipper.setInAnimation(rInAnim);  
                viewFlipper.setOutAnimation(rOutAnim);  
                viewFlipper.showPrevious(); 
                
            
            return true;  
        } else if (e2.getX() - e1.getX() < -120) {        // 从右向左滑动（右进左出）  
            
            if(viewFlipper.getDisplayedChild()==0){
            	//handler线程资源调配Handler.postDelayed(Runnable,long)
            	 new Handler().postDelayed(new Runnable() {
  					
  					@Override
  					public void run() {
  						
 					
 					 Intent intent = new Intent(Welcome.this,MainActivity.class);
 	                startActivity(intent);
 	 				overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
  					}
  				}, 1000);}
            	 Animation lInAnim = AnimationUtils.loadAnimation(mActivity, R.anim.push_left_in);       // 向左滑动左侧进入的渐变效果（alpha 0.1  -> 1.0）  
                 Animation lOutAnim = AnimationUtils.loadAnimation(mActivity, R.anim.push_left_out);     // 向左滑动右侧滑出的渐变效果（alpha 1.0  -> 0.1）  
       
                 viewFlipper.setInAnimation(lInAnim);  
                 viewFlipper.setOutAnimation(lOutAnim);  
                 viewFlipper.showNext();  
               
            
        }  
        return true;  
    }  
  
    @Override  
    public boolean onDown(MotionEvent e) {  
        return false;  
    }  
  
    @Override  
    public void onLongPress(MotionEvent e) {  
    }  
  
    @Override  
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {  
        return false;  
    }  
  
    @Override  
    public void onShowPress(MotionEvent e) {  
    }  
  
    @Override  
    public boolean onSingleTapUp(MotionEvent e) {  
        return false;  
    }  
}  
