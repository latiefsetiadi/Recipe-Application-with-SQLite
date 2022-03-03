package com.example.myrecipe2;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URI;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private Context context;
    private ArrayList<Model> arrayList;

    public Adapter(Context context, ArrayList<Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        Model model = arrayList.get(position);
        //get for view
        final String id = model.getId();
        final String image = model.getImage();
        final String namachef=model.getNamechef();
        final String namafood=model.getNamefood();
        final String alat=model.getBahan();
        final String langkah=model.getLangkah();
        final String addTimeStamp=model.getAddTimeStamp();
        final String updateTimeStamp=model.getUpdateTimeStamp();


        holder.profileIv.setImageURI(Uri.parse(image));
        holder.nameChef.setText(namachef);
        holder.nameFood.setText(namafood);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailDialog(
                        ""+position,
                        ""+id,
                        ""+namachef,
                        ""+namafood,
                        ""+alat,
                        ""+langkah,
                        ""+image,
                        ""+addTimeStamp,
                        ""+updateTimeStamp
                );
            }
        });

    }

    private void detailDialog(String position, final String id, final String namachef, final String namafood, final String alat, final String langkah, final String image, final String addtimestamp, final String updatetimestamp) {

        Intent intent = new Intent(context, Detail_Activity.class);
        intent.putExtra("ID", id);
        intent.putExtra("NAMEFOOD", namafood);
        intent.putExtra("NAMECHEF", namachef);
        intent.putExtra("INGREDIENTS", alat);
        intent.putExtra("STEP", langkah);
        intent.putExtra("IMAGE", image);
        intent.putExtra("ADD_TIMESTAMP", addtimestamp);
        intent.putExtra("UPDATE_TIMESTAMP", updatetimestamp);
        context.startActivity(intent);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView profileIv;
        TextView nameFood,nameChef;
        ImageButton editButton;

        public Holder(@NonNull View itemView) {
            super(itemView);

            profileIv = itemView.findViewById(R.id.profileIv);
            nameFood = itemView.findViewById(R.id.nameF);
            nameChef = itemView.findViewById(R.id.nameC);
            editButton = itemView.findViewById(R.id.editBtn);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
        }
    }
}
