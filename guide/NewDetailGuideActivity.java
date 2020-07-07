package com.example.guide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NewDetailGuideActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ArrayList<String> placelist = new ArrayList<String>();
    ArrayList<Integer> day_detail;
    int count = 0;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail_guide);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("user");

        Intent intent = getIntent();
        final int getnum = intent.getExtras().getInt("day");
        final String place = intent.getExtras().getString("place");

        TextView textView = findViewById(R.id.textView2);
        textView.setText(place);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,placelist);
        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);

        final EditText editText = (EditText)findViewById(R.id.edittext);

        Button add = (Button)findViewById(R.id.button2);
        Button next = (Button)findViewById(R.id.button);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int num = placelist.size();
                String place_list[] = placelist.toArray(new String[placelist.size()]);

                guide Guide = new guide(place, num, place_list);
                Map<String, Object> guideValues = Guide.toMap();


                String key = mDatabase.child("Guide").push().getKey();
                mDatabase.child("Guide").child(key).setValue(guideValues);

                Intent intent = new Intent(NewDetailGuideActivity.this,WriteReviewActivity.class);

                intent.putExtra("day",getnum);
                intent.putExtra("place",place);
                intent.putExtra("key",key);

                startActivity(intent);
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placelist.add(editText.getText().toString());
                adapter.notifyDataSetChanged();
                editText.setText("");
                count++;
            }
        });

    }


}
