package com.tt.com.happyou;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TopActivity extends AppCompatActivity
{

    Button button;

    AlertDialog.Builder mAlertBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        button = (Button)findViewById(R.id.button);

    }

    public void selectKaijyou(final View view){

        final String[] items = {"A関大 A会場","A関大 B会場","B立命 A会場","B立命 B会場" };

        mAlertBuilder = new AlertDialog.Builder(this);
        mAlertBuilder.setTitle(R.string.kaijyou_select);
        mAlertBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String kaijyou_name = new String();

                kaijyou_name = items[which];

                Intent intent = new Intent(view.getContext(),MainActivity.class);
                intent.putExtra("kaijyou_name",kaijyou_name);
                startActivity(intent);

                finish();

            }
        });

        mAlertBuilder.show();

//        kaijyou_name = "A関大 B会場";

    }

}
