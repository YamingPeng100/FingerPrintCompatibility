package com.actimust.seduction;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Scanner extends Activity {
	
	private ImageView print_red;
	private ImageView print_blue;
	
	
    private static final int INVALID_POINTER_ID = -1;

	 // The ‘active pointer’ is the one currently moving our object.

    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.fingers_main);
        print_red = (ImageView) findViewById(R.id.print_red);
        print_red.setVisibility(View.INVISIBLE);
        print_blue = (ImageView) findViewById(R.id.print_blue);
        print_blue.setVisibility(View.INVISIBLE);
        
        LinearLayout mainLayout = (LinearLayout)findViewById(R.id.mainLayout);
        
        mainLayout.setOnTouchListener( onTouchListener);
        
    }
    
    private View.OnTouchListener onTouchListener = new View.OnTouchListener(){

		@Override
		public boolean onTouch(View v, MotionEvent ev) {
			final int action = ev.getAction();
			int yMax = v.getHeight();
			
	        switch (action & MotionEvent.ACTION_MASK) {
		        case MotionEvent.ACTION_DOWN: {
		            final float y = ev.getY();
	//	            
		            if(0<y && y<yMax/2){
		            	//Partie haute de l'ecran
		            	print_red.setVisibility(View.VISIBLE);
		            }else if(yMax/2<y && y<yMax){
		            	//Partie basse de l'ecran
		            	print_blue.setVisibility(View.VISIBLE);
		            }
		            break;
		        }
	//	            
		        case MotionEvent.ACTION_POINTER_DOWN: {
		        	
//		        	final int pointerIndex = (action & MotionEvent.ACTION_POINTER_1_DOWN) 
//			                >> MotionEvent.ACTION_POINTER_1_DOWN;
//			        final int pointerId = ev.getPointerId(pointerIndex);
//			        if (pointerId == mActivePointerId) {
//			            // This was our active pointer going up. Choose a new
//			            // active pointer and adjust accordingly.
//			            final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
//			            mActivePointerId = ev.getPointerId(newPointerIndex);
//			        }
		            final float y = ev.getY(1);
		            
		            if(0<y && y<yMax/2){
		            	//Partie haute de l'ecran
		            	print_red.setVisibility(View.VISIBLE);
		            }else if(yMax/2<y && y<yMax){
		            	//Partie basse de l'ecran
		            	print_blue.setVisibility(View.VISIBLE);
		            }
		            
		            break;
		        }
		        
		        case MotionEvent.ACTION_UP: {
		            final float y = ev.getY();
		            if(0<y && y<yMax/2){
		            	//Partie haute de l'ecran
//		            	print_red.setVisibility(View.INVISIBLE);
		            }else if(yMax/2<y && y<yMax){
		            	//Partie basse de l'ecran
		            }
		            break;
		        }
		        
		        case MotionEvent.ACTION_POINTER_UP: {
		        	
		            final float y = ev.getY();
		            
		            if(0<y && y<yMax/2){
		            	//Partie haute de l'ecran
//		            	print.setVisibility(View.INVISIBLE);
		            }else if(yMax/2<y && y<yMax){
		            	//Partie basse de l'ecran
		            	
		            }
		            
		            break;
		        }
	        }
	        return true;
		}
    };
    
    @Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
    
}