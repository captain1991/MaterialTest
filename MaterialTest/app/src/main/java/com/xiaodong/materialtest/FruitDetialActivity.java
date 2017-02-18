package com.xiaodong.materialtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yxd on 2017/2/17.
 */
public class FruitDetialActivity extends AppCompatActivity {
    int fruit_image_id;
    String fruit_name;
    private ImageView fruit_img;
    private Toolbar toolbar;
    private CollapsingToolbarLayout ctbl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fruitdetail);
        Intent intent = getIntent();
        if(intent!=null){
            fruit_image_id=intent.getIntExtra("fruit_image_id",0);
            fruit_name = intent.getStringExtra("fruit_name");
        }
        fruit_img = (ImageView) findViewById(R.id.fruit_img_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ctbl = (CollapsingToolbarLayout) findViewById(R.id.ctbl);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ctbl.setTitle(fruit_name);
        fruit_img.setImageResource(fruit_image_id);
        TextView content = (TextView) findViewById(R.id.content);
        content.setText(dealText(fruit_name));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.detail_fab);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String dealText(String title){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<500;i++){
            stringBuilder.append(title);
        }
        return stringBuilder.toString();
    }
}
