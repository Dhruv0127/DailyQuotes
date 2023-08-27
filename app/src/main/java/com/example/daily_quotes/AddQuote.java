package com.example.daily_quotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class AddQuote extends AppCompatActivity {

    private EditText quoteEditText;
    private  EditText authorEditText;
    private Button addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote);

        //bind vwies

        quoteEditText = (EditText) findViewById(R.id.editTextText);
        authorEditText = (EditText) findViewById(R.id.editTextText2);
        addButton = (Button) findViewById(R.id.button2);

        //listener
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get text
                String quote = quoteEditText.getText().toString();
                String author = authorEditText.getText().toString();


                //check if empty
                if (quote.isEmpty()){
                    quoteEditText.setError("can not be empty");
                    return;

                }
                if (author.isEmpty()){
                    authorEditText.setError("Cam not be empty");
                    return;
                }

                //add to database
                addQuoteToDB(quote,author);

                // Animate the button and navigate to main page
                animateAndNavigate();

            }
        });

    }

    private void addQuoteToDB(String quote, String author) {
        //create hashmap and instantiate db conn

        HashMap<String, Object> quoteHashMap = new HashMap<>();
        quoteHashMap.put("quoteq1",quote);
        quoteHashMap.put("author1",author);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference quotesRef = database.getReference("quotes");

        String key = quotesRef.push().getKey();
        quoteHashMap.put("key",key);

        quotesRef.child(key).setValue(quoteHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AddQuote.this ,"Added",Toast.LENGTH_SHORT).show();
                quoteEditText.getText().clear();
                authorEditText.getText().clear();

            }
        });
    }
    private void animateAndNavigate() {
        // Load animation
        Animation popAnimation = AnimationUtils.loadAnimation(this, R.anim.pop);

        // Apply animation to button
        addButton.startAnimation(popAnimation);

        // Create an Intent to navigate back to the main page
        Intent intent = new Intent(AddQuote.this, MainActivity.class);
        startActivity(intent);

        // Finish the current activity (optional)
        finish();
    }
}



