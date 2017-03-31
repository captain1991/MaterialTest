package com.xiaodong.materialtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.xiaodong.materialtest.bean.Fruit;

import java.util.List;

/**
 * Created by yxd on 2017/3/31.
 */

public class CustomAdapter extends DelegateAdapter.Adapter<CustomAdapter.ViewHolder> {
    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }
    private LayoutHelper layoutHelper;
    private Context mcontext;
    private List<Fruit> fruitList;
    public CustomAdapter(Context mcontext,List<Fruit> fruitList,LayoutHelper helper) {
        this.mcontext = mcontext;
        this.fruitList=fruitList;
        this.layoutHelper = helper;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.fruits_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Fruit fruit = fruitList.get(position);
        Glide.with(mcontext).load(fruit.getId()).into(holder.fruit_img);
//        holder.fruit_img.setImageResource(fruit.getId());
        holder.fruit_name.setText(fruit.getName());
        holder.fruit_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext,FruitDetialActivity.class);
                intent.putExtra("fruit_image_id",fruit.getId());
                intent.putExtra("fruit_name",fruit.getName());
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fruitList == null ? 0:fruitList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruit_img;
        TextView fruit_name;
        public ViewHolder(View itemView) {
            super(itemView);
            fruit_img = (ImageView) itemView.findViewById(R.id.fruit_img);
            fruit_name = (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }
}
