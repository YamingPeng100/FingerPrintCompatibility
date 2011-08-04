package com.actimust.seduction;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Scanner extends Activity {
	
	private ImageView print_red;
	private ImageView print_blue;
	
	private Vibrator vibrator;
	private long[] pattern = {10,500,100,500,100,500};
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.fingers_main);
        print_red = (ImageView) findViewById(R.id.print_red);
        print_blue = (ImageView) findViewById(R.id.print_blue);
        
        RelativeLayout mainLayout = (RelativeLayout)findViewById(R.id.mainLayout);
        mainLayout.setOnTouchListener( onTouchListener);
        
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        
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
//		            	print_red.setVisibility(View.VISIBLE);
		            	print_red.setVisibility(View.GONE);
		            	vibrator.vibrate(pattern, -1);
		            	animate(R.id.print_red, R.drawable.animation_red);
		            }else if(yMax/2<y && y<yMax){
		            	//Partie basse de l'ecran
//		            	print_blue.setVisibility(View.VISIBLE);
		            	print_blue.setVisibility(View.GONE);
		            	vibrator.vibrate(pattern, -1);
		            	animate(R.id.print_blue, R.drawable.animation_bleu);
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
//		            	print_red.setVisibility(View.VISIBLE);
		            	animateAndShowResult(R.id.print_red, R.drawable.animation_red);
		            }else if(yMax/2<y && y<yMax){
		            	//Partie basse de l'ecran
//		            	print_blue.setVisibility(View.VISIBLE);
		            	animateAndShowResult(R.id.print_blue, R.drawable.animation_bleu);
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
    
    private void animate(int imageViewId, int animationId){
		
		Runnable registerSensorRunnable = new Runnable(){
			@Override
			public void run() {
//				sensorMgr.registerListener(Cloche.this, mAccelerometer, SensorManager.SENSOR_ACCELEROMETER);
			}
		};
		
		ImageView imgView = (ImageView)findViewById(imageViewId);
		imgView.setVisibility(ImageView.VISIBLE);
		imgView.setBackgroundResource(animationId);
		
		registerAnimation(imageViewId, registerSensorRunnable);
		
	}
    
    private void animateAndShowResult(int imageViewId, int animationId){
		
		Runnable registerSensorRunnable = new Runnable(){
			@Override
			public void run() {
				Intent myIntent = new Intent(Scanner.this, Resultat.class);
                startActivity(myIntent);
			}
		};
		
		ImageView imgView = (ImageView)findViewById(imageViewId);
		imgView.setVisibility(ImageView.VISIBLE);
		imgView.setBackgroundResource(animationId);
		
		registerAnimation(imageViewId, registerSensorRunnable);
		
	}

	private void registerAnimation(int id, final Runnable cb){
		final ImageView imgView = (ImageView)findViewById(id);
		final CustomAnimationDrawable aniDrawable = new CustomAnimationDrawable((AnimationDrawable)imgView.getBackground());
		imgView.setBackgroundDrawable(aniDrawable);

		aniDrawable.setOnFinishCallback(cb);
		
		if(!aniDrawable.isRunning()){
			aniDrawable.start();
		}
	}
    
    
    @Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
    
}