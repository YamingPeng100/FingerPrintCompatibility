package com.actimust.seduction;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Scanner extends Activity {
	
	private ImageView print;
	
	private float mPosX;
    private float mPosY;
    
    private float mLastTouchX;
    private float mLastTouchY;

	
    private static final int INVALID_POINTER_ID = -1;

	 // The ‘active pointer’ is the one currently moving our object.
	 private int mActivePointerId = INVALID_POINTER_ID;

    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        print = (ImageView) findViewById(R.id.print);
        print.setVisibility(View.INVISIBLE);
        
        LinearLayout mainLayout = (LinearLayout)findViewById(R.id.mainLayout);
        
        mainLayout.setOnTouchListener( onTouchListener);
        
    }
    
    private View.OnTouchListener onTouchListener = new View.OnTouchListener(){

		@Override
		public boolean onTouch(View v, MotionEvent ev) {
			final int action = ev.getAction();
			int xMax = v.getWidth();
			int yMax = v.getHeight();
			
	        switch (action & MotionEvent.ACTION_MASK) {
	        case MotionEvent.ACTION_DOWN: {
	            final float x = ev.getX();
	            final float y = ev.getY();
//	            
	            if(0<y && y<yMax/2){
	            	print.setVisibility(View.VISIBLE);
	            }
	            mLastTouchX = x;
	            mLastTouchY = y;
//	//
////	            // Save the ID of this pointer
////	            mActivePointerId = ev.getPointerId(0);
	            break;
	        }
//	            
	        case MotionEvent.ACTION_POINTER_DOWN: {
	            // Extract the index of the pointer that left the touch sensor
////	            final int pointerIndex = (action & MotionEvent.ACTION_POINTER_2_DOWN) 
////	                    >> MotionEvent.ACTION_POINTER_2_DOWN;
////	            final int pointerId = ev.getPointerId(pointerIndex);
////	            if (pointerId == mActivePointerId) {
////	                // This was our active pointer going up. Choose a new
////	                // active pointer and adjust accordingly.
////	                final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
////	                mLastTouchX = ev.getX(newPointerIndex);
////	                mLastTouchY = ev.getY(newPointerIndex);
////	                mActivePointerId = ev.getPointerId(newPointerIndex);
////	          OnTouchListener  }
	        	
	            final float y2 = ev.getY();
	            
	            if(0<y2 && y2<yMax/2){
	            	print.setVisibility(View.INVISIBLE);
	            }
	            break;
	        }
	        }
	        return true;
		}
    };
    
//    public boolean onTouchEvent(MotionEvent ev) {
//        
//    	final int action = ev.getAction();
//        switch (action & MotionEvent.ACTION_MASK) {
//        case MotionEvent.ACTION_DOWN: {
//            final float x = ev.getX();
//            final float y = ev.getY();
////            
////            mLastTouchX = x;
////            mLastTouchY = y;
////
////            // Save the ID of this pointer
////            mActivePointerId = ev.getPointerId(0);
//        	
//        	print.setVisibility(View.VISIBLE);
//        	
//            break;
//        }
//            
//        case MotionEvent.ACTION_POINTER_DOWN: {
////            // Extract the index of the pointer that left the touch sensor
////            final int pointerIndex = (action & MotionEvent.ACTION_POINTER_2_DOWN) 
////                    >> MotionEvent.ACTION_POINTER_1_DOWN;
////            final int pointerId = ev.getPointerId(pointerIndex);
////            if (pointerId == mActivePointerId) {
////                // This was our active pointer going up. Choose a new
////                // active pointer and adjust accordingly.
////                final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
////                mLastTouchX = ev.getX(newPointerIndex);
////                mLastTouchY = ev.getY(newPointerIndex);
////                mActivePointerId = ev.getPointerId(newPointerIndex);
////            }
//        	
//        	print.setVisibility(View.INVISIBLE);
//            break;
//        }
//        }
//        
//        return true;
//
//        
//        
////        return super.onTouchEvent(event);
//      }
    
    
}