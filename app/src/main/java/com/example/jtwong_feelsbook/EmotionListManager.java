package com.example.jtwong_feelsbook;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EmotionListManager {
    static final String prefFile = "EmotionList";
    static final String elKey = "emotionList";
    Context context;

    static private EmotionListManager emotionListManager = null;

    public static void initManager(Context context) {
        if (emotionListManager == null) {
            if (context == null) {
                throw new RuntimeException("Missing context for EmotionListManager");
            }
            emotionListManager = new EmotionListManager(context);
        }
    }

    public static EmotionListManager getManager() {
        if (emotionListManager == null) {
            throw new RuntimeException("Did not initialize Manager");
        }
        return emotionListManager;
    }

    public EmotionListManager(Context context) {
        this.context = context;
    }

    public EmotionList loadEmotionList() throws IOException, ClassNotFoundException {
        SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
        String emotionListData = settings.getString(elKey, "");
        if (emotionListData.equals("")) {
            return new EmotionList();
        }
        else {
            return emotionListFromString(emotionListData);
        }
    }

    static public EmotionList emotionListFromString(String emotionListData) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(emotionListData, Base64.DEFAULT));
        ObjectInputStream oi = new ObjectInputStream(bi);
        return (EmotionList)oi.readObject();
    }

    static public String emotionListToString(EmotionList el) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(el);
        oo.close();
        byte bytes[] = bo.toByteArray();
        return Base64.encodeToString(bytes,Base64.DEFAULT);
    }

    public void saveEmotionList(EmotionList el) throws IOException {
        SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(elKey, emotionListToString(el));
        editor.commit();
    }
}
