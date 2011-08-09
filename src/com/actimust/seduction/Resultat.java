package com.actimust.seduction;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Resultat extends Activity {
	
	private TextView resultat;
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.resultat);
        
        resultat = (TextView) findViewById(R.id.resultat);
        
        SharedPreferences settings = getSharedPreferences("pref",0);
        int customResultEnabled  = settings.getInt("customResultEnabled", 1);
        
        if(customResultEnabled == R.id.radio_enable){
        	resultat.setText(settings.getString("result", ""));
        }else{
        	choisirUnResultat();
        }
        
        
        
    }
    
    private void choisirUnResultat() {
		switch(generateRandomBetween(1, 6)){
		case 1 : resultat.setText(R.string.r1);break;
		case 2 : resultat.setText(R.string.r2);break;
		case 3 : resultat.setText(R.string.r3);break;
		case 4 : resultat.setText(R.string.r4);break;
		case 5 : resultat.setText(R.string.r5);break;
		case 6 : resultat.setText(R.string.r6);break;
		
		}
	}

	private int generateRandomBetween(int x,int y){
    	return x + (int)(Math.random() * ((y - x) + 1));
    }
}
