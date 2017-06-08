package com.example.ae.smartvisit.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.activities.HomeActivity;
import com.example.ae.smartvisit.activities.HomeCatListActivity;
import com.example.ae.smartvisit.activities.PlanItemsList;
import com.example.ae.smartvisit.modules.CategoryItem;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by ahmed.E on 6/2/2017.
 */

public class RecyclerAdapterCategories extends RecyclerView.Adapter<RecyclerAdapterCategories.CatViewHolder>  {
    private Context context;
    private ArrayList<CategoryItem> categoryItems;
    private int mode;

    public RecyclerAdapterCategories (Context context, ArrayList<CategoryItem> list,int mode){
        this.context = context;
        categoryItems = list;
        this.mode = mode; //mode describes where i came from homeView or create planView
    }
    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categories_card_item,parent,false);

        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CatViewHolder holder, final int position) {
        holder.background.setImageResource(categoryItems.get(position).getImage());
        holder.title.setText(categoryItems.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mode == 0) {
                    Intent passedIntent = new Intent(context, HomeCatListActivity.class);
                    passedIntent.putExtra("category", categoryItems.get(position).getTitle().toString());
                    context.startActivity(passedIntent);
                    //((HomeActivity)context).finish();
                    // Toast.makeText(context, categoryItems.get(position).getTitle().toString(), Toast.LENGTH_SHORT).show();
                }else {
                    Intent passedIntent = new Intent(context, PlanItemsList.class);
                    passedIntent.putExtra("category", categoryItems.get(position).getTitle().toString());
                    context.startActivity(passedIntent);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryItems.size();
    }

    public class CatViewHolder extends RecyclerView.ViewHolder {
        ImageView background;
        TextView title;

        public CatViewHolder(View itemView) {
            super(itemView);
            background = (ImageView) itemView.findViewById(R.id.categories_card_image);
            title = (TextView) itemView.findViewById(R.id.categories_card_title);
        }
    }
}
