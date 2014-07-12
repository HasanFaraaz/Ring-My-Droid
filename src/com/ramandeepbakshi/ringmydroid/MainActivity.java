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
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.content.SharedPreferences;

public class MainActivity extends Activity {
	
	EditText et;
	ImageButton btn;
	TextView tv;
	public static String keyword;
	SMSProcessor keywordStore;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv = (TextView)findViewById(R.id.textbox1);
		
		et = (EditText)findViewById(R.id.editText1);
		
		//Set text on EditText box from SharedPrefernces
		SharedPreferences settings = getSharedPreferences("KeywordStorage",0); //Open SharedPreferences file named "KeywordStorage". If it does not exist, create it.
		keyword = settings.getString("KeyWord", "RingMyDroid");
		et.setText(keyword);
				
			
		btn = (ImageButton)findViewById(R.id.imageButton2);
		btn.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) //events inside this method occur in sequence when the button is clicked.
			{
				// TODO Auto-generated method stub
				
				keyword = et.getText().toString();	//Store the string in the EditText box 'et' in the String variable 'keyword'
			
						
				//Store the freshly set keyword in SharedPrefernces
				SharedPreferences settings = getSharedPreferences("KeywordStorage",0); //Open the same SharedPreferences file as above
				SharedPreferences.Editor editor = settings.edit(); //call the edit method that returns to the editor instance
				editor.putString("KeyWord", keyword); //Open the string with the key/name - "KeyWord". If it doesn't exist, create it. Put the value of the variable "keyword" in it.
				editor.commit(); //commit or save the edit
			
				//Toast to show new keyword has been set
				Toast toast = Toast.makeText(MainActivity.this, "Key Phrase '"+keyword+"' has been set.", Toast.LENGTH_LONG);
				toast.show();
						 			
			}
			
		});
		
				
	}
	
	 public boolean onCreateOptionsMenu(Menu menu)
	    {
	        MenuInflater menuInflater = getMenuInflater();
	        menuInflater.inflate(R.layout.menu, menu);
	        return true;
	    }
	 
	 /**
	     * Event Handling for Individual menu item selected
	     * Identify single menu item by it's id
	     * */
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item)
	    {
	         
	        switch (item.getItemId())
	        {
	        case R.id.menu_about:
	            // Single menu item is selected do something
	            // Ex: launching new activity/screen or show alert message
	            //Toast.makeText(AndroidMenusActivity.this, "About Developer is Selected", Toast.LENGTH_SHORT).show();
	        	Intent i = new Intent();
				i.setClass(MainActivity.this, AboutActivity.class);
				startActivity(i);
	            return true;
	 
	       
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }    
	 
	
}

