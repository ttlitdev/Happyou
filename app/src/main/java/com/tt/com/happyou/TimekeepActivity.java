package com.tt.com.happyou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TimekeepActivity extends AppCompatActivity {

    TextView kaijyou_name;

    int started_a;
    int started_b;

    int[] people_count_a = {6, 6, 5, 6};
    int[] people_count_b = {6, 5, 6, 4};

    String[] mentor_name_a = {"ダイバー", "のあーる", "りっしー", "ぶっちー"};
    String[] mentor_name_b = {"Tシャツ", "ぽんさこ", "TT", "サカナクション"};

    String[] started_time_a = {"15:01:20", "15:08:14", "15:08:31", "15:16:52", "15:17:02", "15:24:08", "15:24:43", "15:33:25"};
    String[] started_time_b = {"15:00:42", "15:07:12", "15:07:35", "15:13:57", "15:14:09", "15:22:16", "15:22:27", "15:28:10"};

    String[] delay_time_a = {"+0分", "+0分", "+0分", "+1分", "+1分", "+2分", "+2分", "+5分"};
    String[] delay_time_b = {"+0分", "+1分", "+1分", "+1分", "+1分", "+2分", "+2分", "+3分"};

    String started_count;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_timekeep, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timekeep);



        kaijyou_name = findViewById(R.id.kaijyou_name);

        if (getIntent().hasExtra("kaijyou_name")) {
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


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.log_menu) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("kaijyou_name", kaijyou_name.getText().toString());
            intent.putExtra("started_a",started_a);
            intent.putExtra("started_b",started_b);

            startActivity(intent);

            finish();
        } else if (item.getItemId() == R.id.message_menu) {
//            Toast.makeText(this,"タイムキープ画面は未実装です",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MessageActivity.class);
            intent.putExtra("kaijyou_name", kaijyou_name.getText().toString());
            intent.putExtra("started_a",started_a);
            intent.putExtra("started_b",started_b);

            startActivity(intent);

            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refMsg = database.getReference("message");

    public void sendMessage(View v) {
        int id = v.getId();

        Log.d("DEB4", String.valueOf(id));

        String message;
        String kaijyou;
        String[] kaijyou_pass;
        Post post;

        message = new String();

        kaijyou = kaijyou_name.getText().toString();
        kaijyou_pass = kaijyou.split(" ");
        if(kaijyou_pass[1].matches("A会場")){
            started_count = String.valueOf( started_a);
        }
        else
            started_count = String.valueOf(started_b);

        String mentor;
        String timedate;
        String delaytime;
        String call;
        if(kaijyou_pass[1].matches("A会場")) {
            mentor = new String(mentor_name_a[Integer.valueOf(started_count) / 2]);
            timedate = new String(started_time_a[Integer.valueOf(started_count) / 2]);
            delaytime = new String(delay_time_a[Integer.valueOf(started_count) / 2]);
            started_a++;
        }
        else{
            mentor = new String(mentor_name_b[Integer.valueOf(started_count) / 2]);
            timedate = new String(started_time_b[Integer.valueOf(started_count) / 2]);
            delaytime = new String(delay_time_b[Integer.valueOf(started_count) / 2]);
            started_b++;
        }
        if(Integer.valueOf( started_count)%2 == 0){
            call = "開始";
        }
        else
            call = "終了";


        message = new String(mentor+"班 "+call);

        post = new Post(kaijyou_pass[0], kaijyou_pass[1], message, timedate, delaytime ,started_count);

        Log.d("DEB5", "ガヤ");
        Log.d("DEB6", message);
        Log.d("DEB6", kaijyou_pass[0]);
        Log.d("DEB6", kaijyou_pass[1]);

        refMsg.push().setValue(post);

    }
}
