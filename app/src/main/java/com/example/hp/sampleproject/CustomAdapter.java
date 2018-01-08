package com.example.hp.sampleproject;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HP on 11/24/2017.
 */

public class CustomAdapter extends ArrayAdapter {
    class ViewHolder{
        public TextView textView1,textView2;
        public ImageView imageView1;
    }
    private ViewHolder viewholder;

    public CustomAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Movie m = (Movie) getItem(position);
        if(convertView == null){
            viewholder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_items,parent,false);

            viewholder.textView1 = (TextView) convertView.findViewById(R.id.movie_title);
            viewholder.textView2 = (TextView) convertView.findViewById(R.id.type);
            viewholder.imageView1 = (ImageView) convertView.findViewById(R.id.movie_image);
            convertView.setTag(viewholder);
        }else{
            viewholder  = (ViewHolder) convertView.getTag();
        }
        viewholder.textView1.setText(m.getMovie_name());
        viewholder.textView2.setText(m.getDescription());
        //viewholder.imageView1.setImageResource(omar);
        //Toast.makeText(getContext(),m.getImage(),Toast.LENGTH_LONG).show();
        Picasso.with(getContext()).load(m.getImage()).placeholder(R.drawable.omar).into(viewholder.imageView1);

        return convertView;
    }


}

