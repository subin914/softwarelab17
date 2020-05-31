package com.example.guide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.HashMap;


public class NewguideActivity extends MainActivity {

    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newguide);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("user");
        Button nextbtn = (Button)findViewById(R.id.nextbutton);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitGuide();
                startActivity(new Intent(NewguideActivity.this,NewguideActivity.class));
            }
        });

    }

    private  void submitGuide(){
        EditText guideplace = (EditText)findViewById(R.id.guideplace);
        EditText days = (EditText)findViewById(R.id.days);
        final String guide_place = guideplace.getText().toString();
        final String many_days = days.getText().toString();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                writeNewGiude(guide_place,many_days);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void writeNewGiude(String guide_place, String days){
        guide Guide = new guide(guide_place, days);
        Map<String,Object> guideValues = Guide.toMap();

        Map<String,Object> updates = new HashMap<>();
        updates.put("/Guide/",guideValues);

        mDatabase.updateChildren(updates);

    }
}
