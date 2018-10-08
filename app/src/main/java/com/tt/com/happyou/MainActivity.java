package com.tt.com.happyou;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView kaijyou_name;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refMsg = database.getReference("message");

    ListView mListView;
    Button mButton;

    ArrayList<Post> items_all;
    ArrayList<Post> items_a;
    ArrayList<Post> items_b;

    PostAdapter postAdapter_all;
    PostAdapter postAdapter_a;
    PostAdapter postAdapter_b;

    TabLayout tab;

    int[] people_count_a = {6,6,5,6};
    int[] people_count_b = {6,5,6,4};

    String[] mentor_name_a = {"ダイバー", "のあーる", "りっしー", "ぶっちー"};
    String[] mentor_name_b = {"Tシャツ", "ぽんさこ", "TT", "サカナクション"};

    String[] started_time_a = {"15:01:20", "15:08:14", "15:08:31", "15:16:52", "15:17:02", "15:24:08", "15:24:43", "15:33:25"};
    String[] started_time_b = {"15:00:42", "15:07:12", "15:07:35", "15:13:57", "15:14:09", "15:22:16", "15:22:27", "15:28:10"};

    String[] delay_time_a = {"+0分", "+0分", "+0分", "+1分", "+1分", "+2分", "+2分", "+5分"};
    String[] delay_time_b = {"+0分", "+1分", "+1分", "+1分", "+1分", "+2分", "+2分", "+3分"};

    int started_a;
    int started_b;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
  /*
    public void selectTab(View v){

        Log.d("DEB11","dayo");

        if(v.getId() == R.id.tab_a){
            postAdapter.clear();
            for(int i = 0; i<items.size(); i++){
                if(items.get(i).kaijyou_alphabet_name == "A会場"){
                    postAdapter.add(items.get((i)));
                }
            }
        }
        else if(v.getId() == R.id.tab_b){
            postAdapter.clear();
            for(int i = 0; i<items.size(); i++){
                if(items.get(i).kaijyou_alphabet_name == "B会場"){
                    postAdapter.add(items.get((i)));
                }
            }
            Log.d("DEB11","Bdayo");
        }
        else if(v.getId() == R.id.tab_all){
            postAdapter.clear();
            postAdapter.addAll(items);
        }

        mListView.setAdapter(postAdapter);

    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tab = findViewById(R.id.room_tab);

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

        mListView = (ListView)findViewById(R.id.listView);

        items_all = new ArrayList<Post>();
        items_a = new ArrayList<Post>();
        items_b = new ArrayList<Post>();

        postAdapter_all = new PostAdapter(this,0,items_all);
        postAdapter_a = new PostAdapter(this,0,items_a);
        postAdapter_b = new PostAdapter(this,0,items_b);

        mListView.setAdapter(postAdapter_all);


        refMsg.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Post value = dataSnapshot.getValue(Post.class);

                Log.d("DEB7",value.message);

                if(value.kaijyou_alphabet_name.matches("A会場")) {
                    items_a.add(value);
                    items_all.add(value);
                }
                else if(value.kaijyou_alphabet_name.matches("B会場")){
                    items_b.add(value);
                    items_all.add(value);
                }


                postAdapter_all.clear();
                postAdapter_all.addAll(items_all);

                postAdapter_a.clear();
                postAdapter_a.addAll(items_a);

                postAdapter_b.clear();
                postAdapter_b.addAll(items_b);

                postAdapter_all.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("DEB11",tab.getText().toString());

                if(tab.getText().toString().matches("A会場")){
//                    postAdapter.addAll(items_a);
                    mListView.setAdapter(postAdapter_a);
                }
                else if(tab.getText().toString().matches( "B会場")){
//                    postAdapter.clear();
//                    postAdapter.addAll(items_b);
                    mListView.setAdapter(postAdapter_b);
                    Log.d("DEB11","Bdayo");
                }
                else{
                    mListView.setAdapter(postAdapter_all);
//                    postAdapter.clear();
//                    postAdapter.addAll(items_all);
                }

//                postAdapter.notifyDataSetChanged();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

//        Log.d("DEB",String.valueOf(item.getTitle().toString() == "メッセージ"));
//        Log.d("DEB2",item.getTitle().toString());


        if(item.getItemId() == R.id.message_menu){
            Intent intent = new Intent(this,MessageActivity.class);
            intent.putExtra("kaijyou_name",kaijyou_name.getText().toString());
            intent.putExtra("started_a",started_a);
            intent.putExtra("started_b",started_b);
            startActivity(intent);

            finish();
        }
        else if(item.getItemId() == R.id.timekeep_menu){
            Log.d("DEB3",item.getTitle().toString());
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
