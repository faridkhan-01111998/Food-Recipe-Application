package com.example.foodrecipe.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipe.Models.Equipment;
import com.example.foodrecipe.Models.Ingredient;
import com.example.foodrecipe.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionsEquipmentAdapter extends RecyclerView.Adapter<InstructionEqipmentsViewHolder>{
    Context context;
    List<Equipment> list;

    public InstructionsEquipmentAdapter(Context context, List<Equipment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionEqipmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionEqipmentsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions_step_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionEqipmentsViewHolder holder, int position) {

        holder.textView_instructios_step_items.setText(list.get(position).name);
        holder.textView_instructios_step_items.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/equipment_100x100/"+list.get(position).image).into(holder.imageView_instructions_step_items);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionEqipmentsViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView_instructions_step_items;
    TextView textView_instructios_step_items;
    public InstructionEqipmentsViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView_instructions_step_items = itemView.findViewById(R.id.imageView_instructions_step_items);
        textView_instructios_step_items = itemView.findViewById(R.id.textView_instructios_step_items);
    }
}