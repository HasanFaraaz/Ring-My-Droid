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
