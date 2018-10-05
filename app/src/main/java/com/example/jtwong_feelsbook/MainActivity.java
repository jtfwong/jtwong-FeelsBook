package com.example.jtwong_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/** Activity that allows the user to add emotions
 * and move to view the emotion count or previously added emotions */

public class MainActivity extends AppCompatActivity {
    private String buttonText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EmotionListManager.initManager(this.getApplicationContext());
    }

    /** Called when the user taps the button
    * Navigates to recordList activity */
    public void viewList(View view) {
        Intent intent = new Intent(MainActivity.this, recordList.class);
        startActivity(intent);
    }

    /** Called when the user taps the button
     * Navigates to viewEmotionCount activity */
    public void viewCount(View view) {
        Intent intent = new Intent(MainActivity.this, viewEmotionCount.class);
        startActivity(intent);
    }

    /** Called when the user taps the button
     * Adds the clicked emotion to the list of emotions */
    public void addEmotionAction(View v) {
        switch(v.getId()){
            case R.id.loveButton:
                button = (Button)findViewById(R.id.loveButton);
                buttonText = button.getText().toString();
                break;
            case R.id.joyButton:
                button = (Button)findViewById(R.id.joyButton);
                buttonText = button.getText().toString();
                break;
            case R.id.surpriseButton:
                button = (Button)findViewById(R.id.surpriseButton);
                buttonText = button.getText().toString();
                break;
            case R.id.angerButton:
                button = (Button)findViewById(R.id.angerButton);
                buttonText = button.getText().toString();
                break;
            case R.id.sadnessButton:
                button = (Button)findViewById(R.id.sadnessButton);
                buttonText = button.getText().toString();
                break;
            case R.id.fearButton:
                button = (Button)findViewById(R.id.fearButton);
                buttonText = button.getText().toString();
                break;
        }
        Toast.makeText(this, "Added Feeling!", Toast.LENGTH_SHORT).show();
        EmotionListController em = new EmotionListController();
        em.addEmotion(new Emotion(buttonText));
    }
}
