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

import java.util.ArrayList;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.Holder> {

    private Context context;
    private ArrayList<Model> arrayList;
    databaseHelper databasehelper;

    public Adapter2(Context context, ArrayList<Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        databasehelper = new databaseHelper(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.row2, parent, false);
            return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        Model model = arrayList.get(position);
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

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDialog(
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
        holder.editButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog(
                  ""+id
                );
            }
        });
    }

    private void deleteDialog(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Are you want delete?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_delete);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databasehelper.delteInfo(Id);
                ((folder)context).onResume();
                Toast.makeText(context, "Delete Succesfully", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    private void editDialog(String position, final String id, final String namachef, final String namafood, final String alat, final String langkah, final String image, final String addtimestamp, final String updatetimestamp) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update");
        builder.setMessage("Are you want update?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_edit);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(context, EditRecordActivity.class);
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
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        ImageView profileIv;
        TextView nameFood,nameChef;
        ImageButton editButton;
        ImageButton editButton2;

        public Holder(@NonNull View itemView) {
            super(itemView);
            profileIv = itemView.findViewById(R.id.profileIv2);
            nameFood = itemView.findViewById(R.id.nameF2);
            nameChef = itemView.findViewById(R.id.nameC2);
            editButton = itemView.findViewById(R.id.editBtn);
            editButton2 = itemView.findViewById(R.id.editBtn2);
        }
    }
}
