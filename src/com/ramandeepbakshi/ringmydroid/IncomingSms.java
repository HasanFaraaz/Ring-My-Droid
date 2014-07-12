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
	
