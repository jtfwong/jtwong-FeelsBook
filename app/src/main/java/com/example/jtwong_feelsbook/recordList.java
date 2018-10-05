package com.example.jtwong_feelsbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;

/** Activity to view the list of previously added emotions */

public class recordList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);

        EmotionListManager.initManager(this.getApplicationContext());

        ListView listView = (ListView) findViewById(R.id.emotionListView);
        Collection<Emotion> emotions = EmotionListController.getEmotionList().getEmotions();
        final ArrayList<Emotion> list = new ArrayList<Emotion>(emotions);
        final ArrayAdapter<Emotion> emotionAdapter = new ArrayAdapter<Emotion>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(emotionAdapter);

        /** Change observer */
        EmotionListController.getEmotionList().addListener(new Listener() {
            @Override
            public void update() {
                list.clear();
                Collection<Emotion> emotions = EmotionListController.getEmotionList().getEmotions();
                list.addAll(emotions);
                emotionAdapter.notifyDataSetChanged();
            }
        });

        /** Called when the user taps and holds a list item
         * A pop up gives the user the choice of deleting the emotion */
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view,
                                           int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(recordList.this);
                adb.setMessage("Delete "+list.get(position).toString()+"?");
                adb.setCancelable(true);
                final int finalPosition = position;
                adb.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Emotion emotion = list.get(finalPosition);
                        EmotionListController.getEmotionList().removeEmotion(emotion);
                    }
                });
                adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                adb.show();
                return true;
            }
        });

        /** Called when the user taps a list item
         * Navigates to viewRecord activity */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(recordList.this, viewRecord.class);
                startActivity(intent);
            }
        });
    }
}
