/*******************************************************************************
 * Copyright (c) 2014 Ramandeep Singh Bakshi
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge this software into other softwares and/or 
 * publish derivatives of the Software, and to permit persons to whom the 
 * Software is furnished to do so, subject to the following conditions:
 * 
 * - The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * - Due credit should be given to the 'Ring My Droid' app either in print or
 * a link to the 'Ring My Droid' app on Google Play Store.
 *
 * - Due credit should be given to the developer, Ramandeep Singh Bakshi, in print
 * by mentioning the complete name, as well as a link to the official 
 * website 'http://www.ramandeepbakshi.com' or a link to the facebook 
 * page 'https://www.facebook.com/officialrbxi'
 *
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/

package com.ramandeepbakshi.ringmydroid;

import android.os.Bundle;
import android.view.WindowManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

public class SMSProcessor extends Activity
{
			
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		//Make this activity click-through as it is invisible
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
		
		//Read keyword from Shared Preferences and store it in a String 'key'
		SharedPreferences settings = getSharedPreferences("KeywordStorage", 0);
		String key = settings.getString("KeyWord", "");
		
		//Store SMSMessage coming from IncominSMS receiver in a String 'sms'
		Bundle b = getIntent().getExtras();
		String sms = b.getString("SMSMessage");
		
		
		//Compare 'sms' and 'key'
		if(sms.equals(key))
		{
			//Start new activity to play sound.
			Intent i = new Intent();
			i.setClass(SMSProcessor.this, AlarmRinger.class);
			startActivity(i);
		}
		
		finish(); //close this activity after starting new activity
		
			
	}

}	
