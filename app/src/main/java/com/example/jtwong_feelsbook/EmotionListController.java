package com.example.jtwong_feelsbook;

import java.io.IOException;

public class EmotionListController {

    /** Lazy Singleton */
    private static EmotionList emotionList = null;

    /** Warning: throws a runTImeException */
    static public EmotionList getEmotionList() {
        if (emotionList == null) {
            try {
                emotionList = EmotionListManager.getManager().loadEmotionList();
                emotionList.addListener(new Listener() {
                    @Override
                    public void update() {
                        saveEmotionList();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Could not deserialize EmotionList from EmotionListManager");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("Could not deserialize EmotionList from EmotionListManager");
            }
        }
        return emotionList;
    }

    static public void saveEmotionList() {
        try {
            EmotionListManager.getManager().saveEmotionList(getEmotionList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not deserialize EmotionList from EmotionListManager");
        }
    }
    public void addEmotion(Emotion emotion) {
        getEmotionList().addEmotion(emotion);
    }
}
