package com.tt.com.happyou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.zip.Inflater;

public class MessageActivity extends AppCompatActivity {

    TextView kaijyou_name;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refMsg = database.getReference("message");

    Button message1;
    Button message2;

    int started_a;
    int started_b;

    public void sendMessage(View v){
        int id = v.getId();

        Log.d("DEB4",String.valueOf(id));

        String message;
        String kaijyou;
        String[] kaijyou_pass;
        Post post;
        String started_count;



        switch(id){
            case R.id.message_1:

                message = message1.getText().toString();
                kaijyou = kaijyou_name.getText().toString();
                kaijyou_pass = kaijyou.split(" ");

                if(kaijyou_pass[1].matches("A会場")){
                    started_count = String.valueOf( started_a);
                }
                else
                    started_count = String.valueOf(started_b);

                post = new Post(kaijyou_pass[0],kaijyou_pass[1],message," ", " ",started_count);

                Log.d("DEB5","ガヤ");
                Log.d("DEB6",message);
                Log.d("DEB6",kaijyou_pass[0]);
                Log.d("DEB6",kaijyou_pass[1]);

                refMsg.push().setValue(post);
                //finish();

                break;
            case R.id.message_2:


                message = message2.getText().toString();
                kaijyou = kaijyou_name.getText().toString();
                kaijyou_pass = kaijyou.split(" ");

                if(kaijyou_pass[1].matches("A会場")){
                    started_count = String.valueOf( started_a);
                }
                else
                    started_count = String.valueOf(started_b);


                post = new Post(kaijyou_pass[0],kaijyou_pass[1],message," ", " ",started_count);
                refMsg.push().setValue(post);
                //finish();

                break;

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);



        kaijyou_name = findViewById(R.id.kaijyou_name);

        if(getIntent().hasExtra("kaijyou_name")){
            kaijyou_name.setText(getIntent().getStringExtra("kaijyou_name"));
        }

        started_a = 0;
        started_b = 0;

        if(getIntent().hasExtra("started_a")){
            started_a = getIntent().getIntExtra("started_a",started_a);
        }
        if(getIntent().hasExtra("started_b")){
            started_b = getIntent().getIntExtra("started_b",started_b);
        }


        message1 = findViewById(R.id.message_1);
        message2 = findViewById(R.id.message_2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_message,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){


        if(item.getItemId() == R.id.log_menu){
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("kaijyou_name",kaijyou_name.getText().toString());
            intent.putExtra("started_a",started_a);
            intent.putExtra("started_b",started_b);

            startActivity(intent);

            finish();
        }
        else if(item.getItemId() == R.id.timekeep_menu){
            Toast.makeText(this,"タイムキープ画面に遷移します",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,TimekeepActivity.class);
            intent.putExtra("kaijyou_name",kaijyou_name.getText().toString());
            intent.putExtra("started_a",started_a);
            intent.putExtra("started_b",started_b);

            startActivity(intent);

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
