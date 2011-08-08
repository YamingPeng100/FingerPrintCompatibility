package com.actimust.seduction;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class Settings extends Activity {
	
	private Button saveButton;
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.settings);   
        
        SharedPreferences settings = getSharedPreferences("pref",0);
        
        RadioGroup radioGroup_enable = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup_enable.check(settings.getInt("customResultEnabled", 1));
        
        EditText result = (EditText) findViewById(R.id.custom_result);
        result.setText(settings.getString("result", ""));
        
        
        saveButton = (Button) findViewById(R.id.button_settings);
        
     // add a click listener to the button
        saveButton.setOnClickListener(new View.OnClickListener() {
        	
        	RadioGroup radioGroup_enable = (RadioGroup) findViewById(R.id.radio_group);
        	EditText result = (EditText) findViewById(R.id.custom_result);
            public void onClick(View v) {
            	
            	SharedPreferences settings = getSharedPreferences("pref",0);
				SharedPreferences.Editor editor = settings.edit();
				editor.putInt("customResultEnabled", radioGroup_enable.getCheckedRadioButtonId());
				editor.putString("result", result.getText().toString());
				editor.commit();
            }
        });
    }

}
