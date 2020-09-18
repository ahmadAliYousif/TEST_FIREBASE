package com.example.test_firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class Rec_Fire extends RecyclerView.Adapter<Rec_Fire.View_Hold> {
    ArrayList<Det> arrayList;
    Context context;

    public Rec_Fire(ArrayList<Det> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public View_Hold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.det, null, false);
        View_Hold v = new View_Hold(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull View_Hold holder, int position) {
        holder.tv_age.setText(arrayList.get(position).getAge() + "");
        holder.tv_id.setText(arrayList.get(position).getId() + "");
        holder.tv_name.setText(arrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class View_Hold extends RecyclerView.ViewHolder {
        TextView tv_id;
        TextView tv_name;
        TextView tv_age;

        public View_Hold(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.det_id);
            tv_name = itemView.findViewById(R.id.det_name);
            tv_age = itemView.findViewById(R.id.det_age);


        }
    }
}
