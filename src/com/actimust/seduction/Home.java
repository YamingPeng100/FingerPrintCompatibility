package com.actimust.seduction;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Home extends Activity {
	
	private Button bStart;
	private Button bSettings;
	private Button bOtherApp;
	private Button bShare;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.home);
        
        bStart = (Button) findViewById(R.id.button_start);
        bStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Scanner.class);
                startActivity(myIntent);
            }

        });
        
        bSettings= (Button) findViewById(R.id.button_settings);
        bSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Settings.class);
                startActivity(myIntent);
            }

        });
        
        bOtherApp = (Button) findViewById(R.id.button_otherApps);
        bOtherApp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showListApp();
            }

        });
        
        bShare = (Button) findViewById(R.id.button_share);
        bShare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                share();
            }

        });
        
        
    }
    
    private void share(){
    	Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
    	shareIntent.setType("text/plain");
    	shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.app_name));
    	shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>"+getString(R.string.share_message)+
    			" <a href=\"https://market.android.com/details?id=com.actimust.seduction\">download from android market</a></p>"));

    	startActivity(Intent.createChooser(shareIntent, getString(R.string.share)));
    }
    
    private void showListApp() {
    	final CharSequence[] items = {"Make Money", "Cloche", "Hand gun Reality fps"};

    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Other Applications:");
    	builder.setItems(items, new DialogInterface.OnClickListener() {
    	    public void onClick(DialogInterface dialog, int item) {
    	    	switch (item) {
				case 0:
					openAppDetailsPage("market://details?id=fr.first");
					break;
				case 1:
					openAppDetailsPage("market://details?id=com.actimust");
					break;
				case 2:
					openAppDetailsPage("market://details?id=com.actimust.fps");
					break;
				default:
					break;
				}
    	    }
    	});
    	AlertDialog alert = builder.create();
    	alert.show();
		
	}
    
    private void openAppDetailsPage(String uri){
    	Intent intent = new Intent(Intent.ACTION_VIEW);
    	intent.setData(Uri.parse(uri));
    	startActivity(intent);
    }

}
