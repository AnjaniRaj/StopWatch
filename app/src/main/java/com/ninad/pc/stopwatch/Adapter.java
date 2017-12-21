package com.ninad.pc.stopwatch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pc on 11/9/2016.
 */


public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    List<String> list;

    public Adapter(List<String> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.time.setText(list.get(position));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false));
    }
}

class ViewHolder extends RecyclerView.ViewHolder{
    TextView time;
    public ViewHolder(View itemView) {
        super(itemView);
        time= (TextView) itemView.findViewById(R.id.rowtext);
    }
}
