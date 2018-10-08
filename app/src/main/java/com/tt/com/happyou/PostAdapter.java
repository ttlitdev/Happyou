package com.tt.com.happyou;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends ArrayAdapter<Post> {

    List<Post> items;
    String room;

    public PostAdapter (Context context, int resource, List<Post> objects){
        super(context,resource);
        //this.room = room;
        items = objects;
    }

    @Override
    public int getCount(){
        return items.size();
    }

    @Override
    public Post getItem(int position){
        return items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Post item = getItem(position);


            final ViewHolder viewHolder;

            if (convertView != null) {
                viewHolder = (ViewHolder) convertView.getTag();
            } else {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_item_post, parent, false);

                viewHolder = new ViewHolder();
                viewHolder.time = (TextView) convertView.findViewById(R.id.time_text);
                viewHolder.message = (TextView) convertView.findViewById(R.id.message_text);
                viewHolder.delay = (TextView) convertView.findViewById(R.id.delay_text);

                convertView.setTag(viewHolder);
                Log.d("DEB9", "msg_ok");
            }

            Log.d("DEB8", item.getTime());

            viewHolder.time.setText(item.getTime());
            viewHolder.message.setText(item.getMessage());
            viewHolder.delay.setText(item.getDelay());

            return convertView;


    }

    static class ViewHolder{

        TextView time;
        TextView message;
        TextView delay;

    }
}
