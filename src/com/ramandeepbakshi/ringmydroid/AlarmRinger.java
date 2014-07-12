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

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class AlarmRinger extends Activity
{
	ImageButton soundButton;
	MediaPlayer alarm;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.receiver_layout);
		soundButton = (ImageButton)findViewById(R.id.imageButton2);
		
		//Set the media volume to maximum and store current volume so that i can be restored on button click. 
		final AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		final int originalVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
		
		//Set the ringer volume to maximum and store current volume so that i can be restored on button click.
		AudioManager audioManager = (AudioManager)this.getSystemService(AUDIO_SERVICE);
		int streamMaxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
	    audioManager.setStreamVolume(AudioManager.STREAM_RING, streamMaxVolume, AudioManager.FLAG_ALLOW_RINGER_MODES|AudioManager.FLAG_PLAY_SOUND);
		
		//Play sound on loop mode.
		alarm = MediaPlayer.create(AlarmRinger.this, R.raw.alarm);
		alarm.start();
		alarm.setLooping(true);
				
		//Button onClick
		soundButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				alarm.stop(); //Stop alarm
				mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, originalVolume, 0); //Reset media volume to original
				alarm.release(); //Release 'alarm' MediaPlayer instance
				finish(); //Finish the activity
				
			}
			
		});
		
		
	}
	

}
