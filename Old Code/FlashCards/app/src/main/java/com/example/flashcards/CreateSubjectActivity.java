package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class CreateSubjectActivity extends AppCompatActivity {


    // Declare Buttons
    private TextView header;
    private EditText nameInput;
    private Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_subject);

        //Initialise TextView
        header = (TextView) findViewById(R.id.tv_createSubjectHeader);
        //Initialise EditText
        nameInput = (EditText) findViewById(R.id.et_createSubject);
        //Initialise Button
        createButton = (Button) findViewById(R.id.btn_createSubject);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create the subject object and inform the user

                showToast("Subject Created: " + nameInput.toString());

                //Go to the next screen
                Intent intent = new Intent(CreateSubjectActivity.this, MainActivity.class);
            }
        });
    }

    /**
     * A method to show a 'toast' message at the bottom of the screen.
     */
    public void showToast(String text) {
        Toast.makeText(CreateSubjectActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
