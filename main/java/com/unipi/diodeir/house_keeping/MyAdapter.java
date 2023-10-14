package com.unipi.diodeir.house_keeping;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<ListItems> list;

    public MyAdapter(Context context, ArrayList<ListItems> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_list_items,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListItems listItems = list.get(position);
        holder.ID.setText(listItems.getID());
        holder.Area.setText(listItems.getArea());
        holder.Internal_Surface.setText(listItems.getInternal_Surface());
        holder.Price.setText(listItems.getPrice());
        holder.Sale_Rent.setText(listItems.getSale_Rent());
        holder.Type.setText(listItems.getType());
        Picasso.get().load(listItems.getUrl()).fit().centerCrop().into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MetaData.class);
                intent.putExtra("ID",listItems.getID());
                intent.putExtra("Sale_Rent",listItems.getSale_Rent());
                intent.putExtra("Area",listItems.getArea());
                intent.putExtra("Internal_Surface",listItems.getInternal_Surface());
                intent.putExtra("Price",listItems.getPrice());
                intent.putExtra("Type",listItems.getType());
                intent.putExtra("Bathrooms",listItems.getBathrooms());
                intent.putExtra("Bedrooms",listItems.getBedrooms());
                intent.putExtra("Energy_Class",listItems.getEnergy_Class());
                intent.putExtra("Floor",listItems.getFloor());
                intent.putExtra("Heating_Medium",listItems.getHeating_Medium());
                intent.putExtra("Heating_Type",listItems.getHeating_Type());
                intent.putExtra("Year_Build",listItems.getYear_Build());
                intent.putExtra("Url",listItems.getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView ID, Area, Internal_Surface, Price, Sale_Rent, Type;
        ImageView imageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ID = itemView.findViewById(R.id.ID);
            Area = itemView.findViewById(R.id.Area);
            Internal_Surface = itemView.findViewById(R.id.InternalSurface);
            Price = itemView.findViewById(R.id.Price);
            Sale_Rent = itemView.findViewById(R.id.SaleRent);
            Type = itemView.findViewById(R.id.Type);
            imageView = itemView.findViewById(R.id.imageView3);
        }
    }
}
