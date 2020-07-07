package com.example.guide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
        int count = 1;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("user");
        Button nextbtn = (Button)findViewById(R.id.nextbutton);
        final EditText day = (EditText)findViewById(R.id.days);
        final EditText guideplace = (EditText)findViewById(R.id.guideplace);


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitGuide();
                int number = Integer.parseInt(day.getText().toString());
                String guide_place = guideplace.getText().toString();
                Intent intent = new Intent(NewguideActivity.this,NewDetailGuideActivity.class);

                intent.putExtra("day",number);
                intent.putExtra("place",guide_place);
                startActivity(intent);
            }
        });

    }

    private  void submitGuide(){
        EditText guideplace = (EditText)findViewById(R.id.guideplace);
        EditText days = (EditText)findViewById(R.id.days);
        final String guide_place = guideplace.getText().toString();
        final String many_days = days.getText().toString();

    }

    /*private void writeNewGuide(String guide_place, String days) {
        guide Guide = new guide(guide_place, days);
        Map<String, Object> guideValues = Guide.toMap();

        Map<String, Object> updates = new HashMap<>();
        updates.put("/guide place/", guideValues);

        mDatabase.child("Guide").child(guide_place).child("days").setValue(days);
        mDatabase.child("Guide").child(guide_place).child("like").setValue(0);
    }*/
}
