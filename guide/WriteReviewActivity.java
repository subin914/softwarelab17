package com.example.guide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class WriteReviewActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private DatabaseReference mReviewReference;
    List<guide> guideList = new ArrayList<>();
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("user");
        mReviewReference = FirebaseDatabase.getInstance().getReference().child("place-review");

        Intent intent = getIntent();
        final String key = intent.getExtras().getString("key");
        String place = intent.getExtras().getString("place");

        final TextView textView = (TextView)findViewById(R.id.textView3);

        textView.setText(place);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        ListView listView = (ListView)findViewById(R.id._dynamic);
        listView.setAdapter(adapter);

        final EditText edit1 = (EditText)findViewById(R.id.editText);
        final EditText edit2 = (EditText)findViewById(R.id.editText2);
        final EditText edit3 = (EditText)findViewById(R.id.editText3);
        final EditText edit4 = (EditText)findViewById(R.id.editText4);
        final EditText edit5 = (EditText)findViewById(R.id.editText5);

        Button button = (Button)findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReviewReference.child(key).push().setValue(edit1.getText().toString());
                mReviewReference.child(key).push().setValue(edit2.getText().toString());
                mReviewReference.child(key).push().setValue(edit3.getText().toString());
                mReviewReference.child(key).push().setValue(edit4.getText().toString());
                mReviewReference.child(key).push().setValue(edit5.getText().toString());


            }
        });


        mDatabase.child("Guide").child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Guide", dataSnapshot.getValue().toString());
                guide Guide = dataSnapshot.getValue(guide.class);
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
}
