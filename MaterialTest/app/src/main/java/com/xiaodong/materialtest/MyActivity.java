package com.xiaodong.materialtest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.xiaodong.materialtest.bean.Fruit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by yxd on 2017/2/16.
 */
public class MyActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private List<Fruit> fruits = new ArrayList<>();
    private Fruit[] fruitss = new Fruit[]{new Fruit(R.drawable.apple,"苹果"),new Fruit(R.drawable.orange,"橘子"),new Fruit(R.drawable.origin,"桔子"),
            new Fruit(R.drawable.pineapple,"菠萝"),new Fruit(R.drawable.steawberrys,"很多草莓"),new Fruit(R.drawable.strawberry,"草莓"),new Fruit(R.drawable.vegetables,"果篮")};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_myactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        initFruits();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
//        recyclerView.setAdapter(new FruitRecyclerAdapter(this,fruits));
        //天猫框架实现
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0,10);
        //第一种用法
        DelegateAdapter adapter = new DelegateAdapter(layoutManager,false);//第二个参数相同的item时填true，item不同时最好用false（如果用true会有明显卡顿）
        recyclerView.setAdapter(adapter);
        List<DelegateAdapter.Adapter> adapterList = new LinkedList<>();
        adapterList.add(new TitleAdapter(this,"表格"));
        adapterList.add(new CustomAdapter(this,fruits,new GridLayoutHelper(2)));
        adapterList.add(new TitleAdapter(this,"线性"));
        adapterList.add(new CustomAdapter(this,fruits,new LinearLayoutHelper(5)));
        adapterList.add(new TitleAdapter(this,"瀑布"));
        adapterList.add(new CustomAdapter(this,fruits,new StaggeredGridLayoutHelper(2)));
        adapter.setAdapters(adapterList);
        //两种方式添加adapter
//        adapter.addAdapter(new TitleAdapter(this,"表格"));
//        adapter.addAdapter(new CustomAdapter(this,fruits,new GridLayoutHelper(2)));
//        adapter.addAdapter(new TitleAdapter(this,"线性"));
//        adapter.addAdapter(new CustomAdapter(this,fruits,new LinearLayoutHelper(5)));
//        adapter.addAdapter(new TitleAdapter(this,"瀑布流"));
//        adapter.addAdapter(new CustomAdapter(this,fruits,new StaggeredGridLayoutHelper(2)));

        //第二种用法(复杂业务需求时)
//        MyAdapter myAdapter = new MyAdapter(this,fruits,layoutManager);
//        List<LayoutHelper> layoutHelpers = new LinkedList<>();
//        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
//        gridLayoutHelper.setItemCount(8);
//        layoutHelpers.add(gridLayoutHelper);
//        GridLayoutHelper gridLayoutHelper1 = new GridLayoutHelper(4);
//        gridLayoutHelper1.setItemCount(4);
//        layoutHelpers.add(gridLayoutHelper1);
//        myAdapter.setLayoutHelpers(layoutHelpers);
//        recyclerView.setAdapter(myAdapter);

        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navigationView.setCheckedItem(R.id.call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
//                drawerLayout.closeDrawers();
                return true;
            }
        });
        fab.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Delete something",Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MyActivity.this,"Cancle",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.add:
                Toast.makeText(this,"click add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.done:
                Toast.makeText(this,"click done",Toast.LENGTH_SHORT).show();
                break;
            case R.id.write:
                Toast.makeText(this,"click write",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private void initFruits(){
        fruits.clear();
       for(int i=0;i<16;i++){
           Random random = new Random();
           int position = random.nextInt(fruitss.length);
           Fruit fruit = fruitss[position];
           fruit.setName(fruit.getComName());
           fruits.add(fruit);
       }
    }
}
