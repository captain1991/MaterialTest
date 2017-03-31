package com.xiaodong.materialtest;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.bumptech.glide.Glide;
import com.xiaodong.materialtest.bean.Fruit;

import java.util.List;

/**
 * Created by yxd on 2017/3/31.
 */

public class MyAdapter extends VirtualLayoutAdapter<MyAdapter.ViewHolder> {
    private Context mcontext;
    private List<Fruit> fruitList;

    public MyAdapter(Context context,List<Fruit> fruitList,@NonNull VirtualLayoutManager layoutManager) {
        super(layoutManager);
        this.mcontext = context;
        this.fruitList = fruitList;
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
//        int count = 0;
//        List<LayoutHelper> helperList = getLayoutHelpers();
//        for(LayoutHelper helper:helperList){
//            count += helper.getItemCount();
//        }
        return fruitList==null ? 0:fruitList.size();
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
