package com.example.jtwong_feelsbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class EmotionList implements Serializable {
    protected ArrayList<Emotion> emotionList = null;
    protected transient ArrayList<Listener> listeners = null;

    public EmotionList() {
        emotionList = new ArrayList<Emotion>();
        listeners = new ArrayList<Listener>();
    }

    private ArrayList<Listener> getListeners() {
        if (listeners == null) {
            listeners = new ArrayList<Listener>();
        }
        return listeners;
    }

    public Collection<Emotion> getEmotions() {
        return emotionList;
    }

    public void addEmotion(Emotion emotion){
        emotionList.add(emotion);
        notifyListeners();
    }

    public void removeEmotion(Emotion emotion){
        emotionList.remove(emotion);
        notifyListeners();
    }

    public boolean contains(Emotion emotion) {
        return emotionList.contains(emotion);
    }

    private void notifyListeners() {
        for (Listener listener : getListeners()) {
            listener.update();
        }
    }

    public void addListener(Listener l) {
        getListeners().add(l);
    }

    public void removeListener(Listener l) {
        getListeners().remove(l);
    }

}
