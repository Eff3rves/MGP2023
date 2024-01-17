package com.sdm.mgp2023;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.view.SurfaceView;

import java.util.HashMap;
import java.util.Map;

public class AudioManager {
    public final static AudioManager Instance = new AudioManager();

    private Resources res = null;
    private SurfaceView view = null;
    private HashMap<Integer, MediaPlayer> audioMap = new HashMap<Integer, MediaPlayer>();

    private AudioManager()
    {

    }

    public void Init(SurfaceView _view)
    {
        view = _view;
        res = _view.getResources();
    }

    public void PlayAudio(int _id, int left_vol)
    {
        if (audioMap.containsKey(_id))
        {
            audioMap.get(_id).reset();
            audioMap.get(_id).start();
        }

        // Load the audio
        MediaPlayer newAudio = MediaPlayer.create(view.getContext(), _id);
        audioMap.put(_id, newAudio);
        newAudio.start();
    }

    public boolean IsPlaying(int _id)
    {
        if (!audioMap.containsKey(_id))
            return false;

        return audioMap.get(_id).isPlaying();
    }

    public void StopAudio(int _id){
        MediaPlayer curr = audioMap.get(_id);
        curr.stop();
    }

    public void Release(){
        for(Map.Entry<Integer,MediaPlayer>entry:audioMap.entrySet()){
            entry.getValue().stop();
            entry.getValue().reset();
            entry.getValue().release();
        }

        //empty it
        audioMap.clear();
    }

    private  MediaPlayer GetAudio(int _id){

        //check if audio is loaded or not
        if(audioMap.containsKey(_id))
            //has the clip return it
            return audioMap.get(_id);

        //load the audio if not
        MediaPlayer result = MediaPlayer.create(view.getContext(), _id);
        audioMap.put(_id,result);
        return result;
    }
}
