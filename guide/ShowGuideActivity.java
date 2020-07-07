package com.example.guide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowGuideActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private DatabaseReference mReviewReference;
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_guide);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("user");

        final TextView textView = (TextView)findViewById(R.id.textView4);

        ListView listView = (ListView)findViewById(R.id.listview);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        //key는 다른 activity(아직 안만들었음)에서 intent로 받아옴

        Button likebtn = (Button)findViewById(R.id.button4);
        likebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("Guide").child(key)
            }
        });

        mDatabase.child("Guide").child(key).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Guide", dataSnapshot.getValue().toString());
                guide Guide = dataSnapshot.getValue(guide.class);
                textView.setText(Guide.guide_place);
                list.add(Guide.place1);
                list.add(Guide.place2);
                list.add(Guide.place3);
                list.add(Guide.place4);
                list.add(Guide.place5);
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onStarClicked(DatabaseReference guideref){
        guideref.child(key).runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                guide g = mutableData.getValue(guide.class);
                g.likes = g.likes +1;
                return null;
            }

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

            }
        });
    }



}
