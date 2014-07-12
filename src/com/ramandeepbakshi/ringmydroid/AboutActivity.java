package com.ramandeepbakshi.ringmydroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class AboutActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
    }
	
	public void GooglePlayClick(View view){
		Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("market://search?q=pub:Ramandeep Singh Bakshi"));
		startActivity(intent);
	}
	
	public void OpenWebsite(View view){
		Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.ramandeepbakshi.com"));
		startActivity(intent);
	}

}
