package com.example.jtwong_feelsbook;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/** Activity to view the time and comment of the selected emotion
 * Also allows the user to edit the time and comment of the emotion
 * and delete emotions
 * Not completely implemented */

public class viewRecord extends AppCompatActivity {

    public TextView commentView;
    public AlertDialog commentDialog;
    public EditText commentEdit;

    public TextView timeView;
    public AlertDialog timeDialog;
    public EditText timeEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);
        EmotionListManager.initManager(this.getApplicationContext());

        /** Creates popup window to edit the comment */
        commentView = (TextView) findViewById(R.id.commentView);
        commentDialog = new AlertDialog.Builder(this).create();
        commentEdit = new EditText(this);

        commentDialog.setTitle("Edit Comment");
        commentDialog.setView(commentEdit);

        commentDialog.setButton(DialogInterface.BUTTON_POSITIVE, "SAVE COMMENT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                commentView.setText(commentEdit.getText());
            }
        });

        commentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentEdit.setText(commentView.getText());
                commentDialog.show();
            }
        });

        /** Creates popup window to edit the time */
        timeView = (TextView) findViewById(R.id.timeView);
        timeDialog = new AlertDialog.Builder(this).create();
        timeEdit = new EditText(this);

        timeDialog.setTitle("Edit Time");
        timeDialog.setView(timeEdit);

        timeDialog.setButton(DialogInterface.BUTTON_POSITIVE, "SAVE TIME", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                timeView.setText(timeEdit.getText());
            }
        });

        timeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeEdit.setText(timeView.getText());
                timeDialog.show();
            }
        });
    }
}
