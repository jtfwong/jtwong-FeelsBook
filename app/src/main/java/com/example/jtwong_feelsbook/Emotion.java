package com.example.jtwong_feelsbook;

import java.io.Serializable;

public class Emotion implements Serializable {
    protected String emotionName;

    public Emotion(String emotionName) {
        this.emotionName = emotionName;
    }

    public String getEmotionName() {
        return this.emotionName;
    }

    public String toString() {
        return getEmotionName();
    }

    public boolean equals(Object compareEmotion) {
        if (compareEmotion != null && compareEmotion.getClass() == this.getClass()) {
            return this.equals((Emotion)compareEmotion);
        }
        else {
            return false;
        }
    }

    public boolean equals(Emotion compareEmotion) {
        if(compareEmotion == null) {
            return false;
        }
        return getEmotionName().equals(compareEmotion.getEmotionName());
    }

    public int hashCode() {
        return ("Emotion:" + getEmotionName()).hashCode();
    }
}
