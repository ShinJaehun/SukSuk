package com.shinjaehun.suksuk;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by shinjaehun on 2016-06-09.
 */
public class Effects {

    private static final String TAG = Effects.class.toString();

    private static final Effects INSTANCE = new Effects();

    public static final int SOUND_1 = 1;
    public static final int SOUND_2 = 2;

    private Effects() {
    }

    public static Effects getInstance() {
        return INSTANCE;
    }

    private SoundPool soundPool;
    private HashMap<Integer, Integer> soundPoolMap;
//    int priority = 1;
//    int no_loop = 0;
//    private int volume;
//    float normal_playback_rate = 1f;

    private Context context;

    public void init(Context context) {
        this.context = context;
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        soundPoolMap = new HashMap<Integer, Integer>();
        soundPoolMap.put(SOUND_1, soundPool.load(context, R.raw.beep, 1));
        soundPoolMap.put(SOUND_2, soundPool.load(context, R.raw.tada, 1));

//        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//        volume = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
    }

    public void playBeep(int soundId) {
//        Log.i(TAG, "!!!!!!!!!!!!!! playSound_1 !!!!!!!!!!");
//        soundPool.play(soundId, volume, volume, priority, no_loop, normal_playback_rate);
        soundPool.play(soundId, 1, 1, 0, 0, 1);
//        Toast.makeText(context, "Now you can hear sound effect!", Toast.LENGTH_LONG).show();
    }

    public void playTada(int soundId) {
        soundPool.play(soundId, 1, 1, 0, 0, 1);
    }
}
