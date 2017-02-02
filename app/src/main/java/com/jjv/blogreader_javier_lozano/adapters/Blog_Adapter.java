package com.jjv.blogreader_javier_lozano.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.jjv.blogreader_javier_lozano.R;
import com.jjv.blogreader_javier_lozano.objects.Blog;

import java.util.ArrayList;

/**
 * Created by javi0 on 01/02/2017.
 */

public class Blog_Adapter extends BaseAdapter {
    private Context context;
    private Blog[] blogs;

    public Blog_Adapter(Context context, Blog[] blogs) {
        this.context = context;
        this.blogs = blogs;
    }

    @Override
    public int getCount() {
        return blogs.length;
    }

    @Override
    public Object getItem(int position) {
        return blogs[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.blog_item,null);
            holder = new ViewHolder();
            holder.lbl_titulo = (TextView) convertView.findViewById(R.id.lbl_titulo);
            holder.lbl_autor = (TextView) convertView.findViewById(R.id.lbl_autor);
            convertView.setTag(holder);

        } else {
            holder =(ViewHolder) convertView.getTag();
        }
        holder.lbl_titulo.setText(blogs[position].getTitulo());
        holder.lbl_autor.setText(blogs[position].getAutor());
        return convertView;
    }

    private class ViewHolder {
        TextView lbl_titulo;
        TextView lbl_autor;
    }


}
