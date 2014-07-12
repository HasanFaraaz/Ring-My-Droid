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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class IncomingSms extends BroadcastReceiver {
	
	@Override
	    public void onReceive(Context context, Intent intent) 
	    {
	        //---get the SMS message passed in---
	        Bundle bundle = intent.getExtras();        
	        SmsMessage[] msgs = null;
	        String sms = null;
	        //String str = "";      
	        
	      	        
	        if (bundle != null)
	        {
	            //---retrieve the SMS message received---
	            Object[] pdus = (Object[]) bundle.get("pdus");
	            msgs = new SmsMessage[pdus.length];            
	            for (int i=0; i<msgs.length; i++){
	                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                
	                //str += "SMS from " + msgs[i].getOriginatingAddress();                     
	               // str += " :";
	               // str += msgs[i].getMessageBody().toString();
	               // str += "\n";   
	                sms = msgs[i].getMessageBody().toString();
	                	                	                
	            }
	            
	            
	          //Start new activity to play sound.
				Intent i = new Intent();
				i.putExtra("SMSMessage",sms);
				i.setClass(context, SMSProcessor.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(i);
					
				//---display the new SMS message---
	            //Toast.makeText(context, key, Toast.LENGTH_SHORT).show();
	            
	        }                         
	    }
	
}
	
